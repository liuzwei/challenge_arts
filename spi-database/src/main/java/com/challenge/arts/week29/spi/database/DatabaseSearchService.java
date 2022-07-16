package com.challenge.arts.week29.spi.database;

import java.util.List;

import com.challenge.arts.week29.spi.SpiSearch;

/**
 * @author liuzhaowei
 * @date 2022/7/15 10:24
 */
public class DatabaseSearchService implements SpiSearch {
    @Override
    public List<String> searchDocs(String keyWords) {
        System.out.println("数据库搜索，keyWords="+keyWords);
        return null;
    }
}
