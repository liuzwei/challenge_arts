## Netflix内容工程如何使联合图可搜索

原文：[How Netflix Content Engineering makes a federated graph searchable](https://netflixtechblog.com/how-netflix-content-engineering-makes-a-federated-graph-searchable-5c0c1c7d7eaf)

#### 摘要理解
在Netflix中，有三个实体，Movie、Production、Talent，其中这三个实体来自于不用的微服务。如果每个实体可用，那么在某些业务中需要对三个实体进行管理查询，所以某些服务可能提供他不拥有的数据，甚至一些过滤查询条件，如果这个问题出在Movie所在的服务，那么当然也会出现在其他实体所在的服务。
为了解决这个问题，Netflix研发了Studio Search平台。

Studio搜索平台的核心是，把三个实体的关键信息根据实体之间的关联关系合并到一起，这样就可以通过合在一起的关键信息进行筛选、排名、分页等。

选用Elasticsearch来做存储。就是将Movie、Production、Talent三个实体基于之间的关联关系，将在业务中最可能用到的字段组合到一块，形成一个大的索引，存储在ES中，并且这个索引要和三个实体的服务之间保持状态的一致，比如实体中某些字段更新了，这些字段又存在于合并的索引中，所以各服务更新了字段也要更新ES中的索引。

为了保持索引最新，采用事件的机制用来做触发，重新编制索引更新到ES中。


