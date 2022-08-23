
### 1. What is BaseTypeHandler

In mybatis framework, `BaseTypeHandler` is an abstract class which support filting data between database and application. When you read some field from a database like mysql, you can handle the field and process the `String` field "name1, name2" to `List` ["name1","name2"]. Similarly, it can be handled in the opposite way.

If you want to handle the data, you must implement four methods of `BaseTypeHandler`.

```java
  public abstract void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

  public abstract T getNullableResult(ResultSet rs, String columnName) throws SQLException;

  public abstract T getNullableResult(ResultSet rs, int columnIndex) throws SQLException;

  public abstract T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException;
```

The `setNonNullParameter` method is set value for the field. and the other methods are get value.


### 2. Handle geometry of Mysql

Mysql has the feature of processing spatial functions, like query some records nearby a point. The premise is that you must set the filed type to geometry, like this:
```SQL
CREATE TABLE `company` (
  `id` bigint NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `longitude` varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `latitude` varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `geometry` geometry DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='企业信息表';
``` 

### 3. Overide the methods of BaseTypeHandler

Because of use mybatis-plus,so we must dependence the component :
```xml
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.5.1</version>
    </dependency>
```
and we will handle the geometry and SRID, so we import other component :
```xml
    <dependency>
        <groupId>org.locationtech.jts</groupId>
        <artifactId>jts-core</artifactId>
        <version>1.18.2</version>
    </dependency>
```
if you want learn more about this component you can click here [jts-core](https://github.com/locationtech/jts).

Now, override the methods, below is the code:
```java
@Component
@MappedTypes({String.class})
@MappedJdbcTypes({JdbcType.OTHER})
public class GeometryTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        Geometry geo;
        try {
            // String转Geometry
            geo = new WKTReader(new GeometryFactory(new PrecisionModel())).read(s);
            // Geometry转WKB
            byte[] geometryBytes = new WKBWriter(2, ByteOrderValues.LITTLE_ENDIAN, false).write(geo);
            // 设置SRID为mysql默认的 0
            byte[] wkb = new byte[geometryBytes.length + 4];
            wkb[0] = wkb[1] = wkb[2] = wkb[3] = 0;
            System.arraycopy(geometryBytes, 0, wkb, 4, geometryBytes.length);
            preparedStatement.setBytes(i, wkb);
        } catch (ParseException e) {

        }
    }


    @Override
    public String getNullableResult(ResultSet resultSet, String s) {
        try (InputStream inputStream = resultSet.getBinaryStream(s)) {
            Geometry geo = getGeometryFromInputStream(inputStream);
            if (geo != null) {
                return geo.toString();
            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) {
        try (InputStream inputStream = resultSet.getBinaryStream(i)) {
            Geometry geo = getGeometryFromInputStream(inputStream);
            if (geo != null) {
                return geo.toString();
            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return "";
    }

    /**
     * 流转 geometry
     */
    private Geometry getGeometryFromInputStream(InputStream inputStream) throws Exception {
        Geometry dbGeometry = null;

        if (inputStream != null) {
            // 二进制流转成字节数组
            byte[] buffer = new byte[255];

            int bytesRead = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // 得到字节数组
            byte[] geometryAsBytes = baos.toByteArray();
            // 字节数组小于5 异常
            if (geometryAsBytes.length < 5) {
                throw new RuntimeException("坐标异常");
            }

            //字节数组前4个字节表示srid 去掉
            byte[] sridBytes = new byte[4];
            System.arraycopy(geometryAsBytes, 0, sridBytes, 0, 4);
            boolean bigEndian = (geometryAsBytes[4] == 0x00);
            // 解析srid
            int srid = 0;
            if (bigEndian) {
                for (int i = 0; i < sridBytes.length; i++) {
                    srid = (srid << 8) + (sridBytes[i] & 0xff);
                }
            } else {
                for (int i = 0; i < sridBytes.length; i++) {
                    srid += (sridBytes[i] & 0xff) << (8 * i);
                }
            }

            WKBReader wkbReader = new WKBReader();
            // WKBReader 把字节数组转成geometry对象。
            byte[] wkb = new byte[geometryAsBytes.length - 4];
            System.arraycopy(geometryAsBytes, 4, wkb, 0, wkb.length);
            dbGeometry = wkbReader.read(wkb);
            dbGeometry.setSRID(srid);
        }
        return dbGeometry;
    }
}
```

Next is how to use class `GeometryTypeHandler` . In `company` entity, usually we will use the annotation `@TableField`, and we can set `typeHandler` property,like this:
```java
    @TableField(typeHandler = GeometryTypeHandler.class)
    private String geometry;
```

The method `setNonNullParameter` of `GeometryTypeHandler` whill be invoked  when we save or update the `Company` entity. And the method `getNullableResult` and so on other get methods will be invoked when we query the `Company` entity.

Be carefully, the method `setNonNullParameter` will be invoked when we save entity , but the method `getNullableResult` will not be invoked when we query it, you must set `autoResultMap` property for annotation `@TableName`, the code is:
```java
@TableName(value = "company", autoResultMap = true)
public class Company {
    // some other properties
}
```

### 4. Unit test

**Save Company Entity**
```java
    public Long saveCompany(Company company) {
        company.setGeometry("POINT("+company.getLatitude()+" "+company.getLongitude()+")");
        companyRepository.save(company);
        return company.getId();
    }
```


**Search Company by point and distance**
```java
public List<Company> searchNearby(String longitude, String latitude, Long kilometers) {

        return companyRepository.lambdaQuery()
            .apply("ST_Distance_Sphere(geometry, POINT ({0},{1}))<={2}", longitude, latitude, kilometers)
            .list();
    }
```

### 5. Source Code

THis is the source code of the demo. [mysql-geometry-demo](https://github.com/liuzwei/mysql-geometry-demo)


