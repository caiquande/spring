package com.galaxy.libra.app.dom.biz.service.es;


import com.galaxy.libra.app.dom.biz.entity.client.EsClient;
import com.galaxy.libra.app.dom.biz.service.DataDML;
import com.galaxy.libra.app.dom.biz.vo.es.FutureListener;
import com.galaxy.libra.app.dom.biz.vo.es.SearchContainer;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.entity.dataMg
 * @date 2019-09-01
 * @time 17:37
 * @p_name bigdataplatform
 */
@Service
public class EsDML implements DataDML {

    public void indexExistAsync(EsClient esClient, String indexName, FutureListener listener) {
        final GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        esClient.getClient().indices().existsAsync(getIndexRequest, RequestOptions.DEFAULT, listener);
    }

    public void indexExist(EsClient esClient, String indexName) throws Exception{
        final GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        final boolean exists = esClient.getClient().indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // create index
    public void createIndexAsync(EsClient esClient,String indexName, Map<String, Object> mapping, FutureListener listener) throws Exception {
        final CreateIndexRequest indexRequest = new CreateIndexRequest(indexName);
        indexRequest.mapping(new HashMap<String, Object>() {
            {
                put("properties", mapping);
            }
        });
        try {
            esClient.getClient().indices().createAsync(indexRequest, RequestOptions.DEFAULT, listener);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //insert and update single doc
    public void insertSingleDocAsync(EsClient esClient,String indexName, String id, Map<String, Object> source, FutureListener listener) throws Exception {
        final IndexRequest indexRequest = new IndexRequest(indexName).id(id).source(source);
        esClient.getClient().indexAsync(indexRequest, RequestOptions.DEFAULT, listener);
    }

    public void updateSingleDocAsync(EsClient esClient,String indexName, String id, Map<String, Object> updates, FutureListener listener) throws Exception {
        final UpdateRequest updateRequest = new UpdateRequest(indexName, id).doc(updates);
        esClient.getClient().updateAsync(updateRequest, RequestOptions.DEFAULT, listener);
    }

    //insert and update bulk
    public void bulkInsert(EsClient esClient,String indexName, List<Map<String, Object>> source, String id, FutureListener listener) throws Exception {
        final BulkRequest bulkRequest = new BulkRequest();
        source.forEach(e -> {
            bulkRequest.add(new IndexRequest(indexName).id(e.get(id).toString()).source(e));
        });
        esClient.getClient().bulkAsync(bulkRequest, RequestOptions.DEFAULT, listener);
    }

    // search singel doc
    public Object searchById(EsClient esClient,String index, String id, String idVal, String include, String exclude) throws Exception {
        final SearchRequest searchRequest = new SearchRequest(index)
                .source(new SearchSourceBuilder().query(QueryBuilders.termQuery(id, idVal)).fetchSource(include, exclude));
        final SearchResponse searchResponse = esClient.getClient().search(searchRequest, RequestOptions.DEFAULT);
        final Optional<SearchResponse> optionalSearchResponse = Optional.ofNullable(searchResponse);
        if (optionalSearchResponse.isPresent()) {
            final SearchHit[] hits = optionalSearchResponse.get().getHits().getHits();
            if (hits.length > 0) {
                return hits[0].getSourceAsMap().get(include);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //search by request
    public List<Object> searchByRequest(EsClient esClient, SearchRequest searchRequest, SearchContainer container, int flag) throws Exception {
        final ArrayList<Object> res = new ArrayList<Object>();
        final SearchResponse searchResponse = esClient.getClient().search(searchRequest, RequestOptions.DEFAULT);
        final Optional<SearchResponse> optionalSearchResponse = Optional.ofNullable(searchResponse);
        final String singleFieldName = container.getFieldsName().get(flag);
        if (optionalSearchResponse.isPresent()) {
            final SearchHit[] hits = optionalSearchResponse.get().getHits().getHits();
            if (hits.length > 0) {
                for (SearchHit hit : hits) {
                    res.add(hit.getSourceAsMap().get(singleFieldName));
                }
            }
        }
        return res;
    }

}
