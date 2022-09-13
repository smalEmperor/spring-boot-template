package com.template.util;

import com.template.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * @author df
 */
@Slf4j
//@Component
public class EsClientUtil {

    @Autowired
    @Qualifier(value = "restClient")
    private RestHighLevelClient restHighLevelClient;

    //创建索引
    public boolean createIndex(String indexName) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        try {
            CreateIndexResponse response = restHighLevelClient.indices()
                    .create(createIndexRequest, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (Exception e) {
            log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
        }
        return false;
    }

    /**
     * 索引是否存在
     */
    public boolean existIndex(String indexName) {
        GetIndexRequest request = new GetIndexRequest(indexName);
        try {
            return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
        }
        return false;
    }

    /**
     * 删除索引
     */
    public boolean deleteIndex(String indexName) {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        try {
            AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            return delete.isAcknowledged();
        } catch (Exception e) {
            log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
        }
        return false;
    }

    /**
     * 添加文档
     */
    public boolean createDocument(String indexName, String id, String jsonString) {
        IndexRequest request = new IndexRequest(indexName);
        request.id(id);
        request.timeout(TimeValue.timeValueSeconds(1));
        //将我们的数据放入请求，json
        request.source(jsonString, XContentType.JSON);
        //客服端发送请求
        try {
            IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            if (index.getResult() == DocWriteResponse.Result.CREATED) {
                return true;
            }
        } catch (Exception e) {
            log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
        }
        return false;
    }

    /**
     * 添加文档
     */
    public boolean createAsyncDocument(String indexName, String id, String jsonString) {
        IndexRequest request = new IndexRequest(indexName);
        request.id(id);
        request.timeout(TimeValue.timeValueSeconds(1));
        //将我们的数据放入请求，json
        request.source(jsonString, XContentType.JSON);

        //客服端发送请求
        restHighLevelClient.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {

            }

            @Override
            public void onFailure(Exception e) {
                log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
                e.printStackTrace();
            }
        });
        return true;
    }

    /**
     * 添加文档
     */
    public boolean createDocument(String indexName, String id, Map<String, Object> jsonString) {
        IndexRequest request = new IndexRequest(indexName);
        request.id(id);
        request.timeout(TimeValue.timeValueSeconds(1));
        //将我们的数据放入请求，json
        request.source(jsonString);
        //客服端发送请求
        try {
            IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            if (index.getResult() == DocWriteResponse.Result.CREATED) {
                return true;
            }
        } catch (Exception e) {
            log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
        }
        return false;
    }

    /**
     * 添加文档
     */
    public boolean createAsyncDocument(String indexName, String id,  Map<String, Object> jsonString) {
        IndexRequest request = new IndexRequest(indexName);
        request.id(id);
        request.timeout(TimeValue.timeValueSeconds(1));
        //将我们的数据放入请求，json
        request.source(jsonString);
        //客服端发送请求
        restHighLevelClient.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {

            }

            @Override
            public void onFailure(Exception e) {
                log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
                e.printStackTrace();
            }
        });
        return true;
    }

    /**
     * 判断是否存在文档
     */
    public boolean isExist(String indexName, String id) {
        GetRequest getRequest = new GetRequest(indexName, id);
        //不获取返回的source的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        try {
            return restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    //获取文档信息
    public Map<String, Object> getDocument(String indexName, String id) {
        GetRequest getRequest = new GetRequest(indexName, id);
        try {
            GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            return response.getSourceAsMap();
        } catch (Exception e) {
            log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }

    //更新文档信息
    public boolean updateDocument(String indexName, String id, String jsonString) {
        UpdateRequest request = new UpdateRequest(indexName, id);
        request.doc(jsonString, XContentType.JSON);
        try {
            UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);
            if (update.getResult() == DocWriteResponse.Result.UPDATED) {
                return true;
            }
        } catch (Exception e) {
            log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    //删除文档
    public boolean deleteDocument(String indexName, String id) {
        DeleteRequest request = new DeleteRequest(indexName, id);
        try {
            DeleteResponse update = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
            if (update.getResult() == DocWriteResponse.Result.DELETED) {
                return true;
            }
        } catch (IOException e) {
            log.error("框架捕获到异常:[{}][{}]", Result.ERROR, e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 批量插入数据
     * @param indexName 索引
     * @param valueMap  map
     * @return BulkResponse
     * @throws IOException 异常
     */
    public BulkResponse bulkInsert(String indexName, Map<String, String> valueMap) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        Set<String> keySet = valueMap.keySet();
        for (String id : keySet) {
            IndexRequest request = new IndexRequest(indexName);
            request.id(id).source(valueMap.get(id), XContentType.JSON);
            bulkRequest.add(request);
        }
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 根据QueryBuilder来查询全部的条数
     *
     * @param indexName 索引
     * @param id   id
     * @return 条数
     * @throws IOException 异常
     */
    public SearchResponse idQuery(String[] indexName, String id) throws IOException {
        SearchRequest searchRequest = new SearchRequest(filterIndexName(indexName));
        searchRequest.source().query(QueryBuilders.idsQuery().addIds(id));
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    /**
     * 根据QueryBuilder来查询全部的条数
     *
     * @param indexNameArray 索引
     * @param builder   查询构建器
     * @return 条数
     * @throws IOException 异常
     */
    public Long getTotalRecords(String[] indexNameArray, QueryBuilder builder) throws IOException {
        String[] indexArr = filterIndexName(indexNameArray);
        if(indexArr.length == 0) {
            return 0L;
        }
        SearchRequest searchRequest = new SearchRequest(indexArr);
        searchRequest.source().query(builder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return response.getHits().getTotalHits().value;
    }

    /**
     * 查询全部
     *
     * @param indexName 索引
     * @param builder   查询构建器
     * @return SearchResponse
     * @throws IOException 异常
     */
    public SearchResponse findAll(String indexName, QueryBuilder builder) throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        request.source().query(builder);
        return restHighLevelClient.search(request, RequestOptions.DEFAULT);
    }

    /**
     * 分页
     *
     * @param indexNameArray    索引
     * @param queryBuilder 参数
     * @param from         从第几条
     * @param size         页面条数
     * @param sortBuilder  排序
     * @return response
     * @throws IOException 异常
     */
    public SearchResponse page(String[] indexNameArray, QueryBuilder queryBuilder, int from, int size, SortBuilder sortBuilder)
            throws IOException {
        String[] indexArr = filterIndexName(indexNameArray);
        if(indexArr.length == 0){
            return null;
        }
        SearchRequest request = new SearchRequest(indexArr);
        //构建搜寻器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //参数
        sourceBuilder.query(queryBuilder);
        //排序
        sourceBuilder.sort(sortBuilder);
        //分页
        sourceBuilder.from(from);
        sourceBuilder.size(size);

        request.source(sourceBuilder);
        return restHighLevelClient.search(request, RequestOptions.DEFAULT);
    }

    /**
     * 批量更新
     */
    public void update(String indexName, Map<String, String> valueMap)throws IOException{
        bulkInsert(indexName,valueMap);
    }

    /**
     * 过滤不存在的indexName
     *
     * @param indexNameArray 索引数组
     * @return 索引数组
     * @throws IOException 异常
     */
    private String[] filterIndexName(String[] indexNameArray) throws IOException {
        List<String> resultList = new ArrayList<>();
        for (String indexName : indexNameArray) {
            GetIndexRequest request = new GetIndexRequest(indexName);
            boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
            if (exists) {
                resultList.add(indexName);
            }
        }
        int size = resultList.size();
        return resultList.toArray(new String[size]);
    }
}
