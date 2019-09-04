package com.galaxy.libra.dom.biz.service.es;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import com.galaxy.libra.dom.biz.service.DataDQL;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Max;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Optional;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.service.es
 * @date 2019-09-01
 * @time 15:34
 * @p_name bigdataplatform
 */
public class EsDQL implements DataDQL {

    public SearchRequest genSearchRequest(String indexName) {
        final SearchRequest searchRequest = new SearchRequest(indexName);
        return searchRequest;
    }

    public SearchSourceBuilder genSearchSourceBuilder(BoolQueryBuilder bqb) {
        final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .query(bqb)
        ;
        return searchSourceBuilder;
    }

    public SearchSourceBuilder genSearchSourceBuilder(String insuredNo, String riskCode, String polNo, BoolQueryBuilder bqb) {

        final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .query(QueryBuilders.termQuery("insuredno", insuredNo))
                .query(QueryBuilders.termQuery("riskcode", riskCode))
                .query(QueryBuilders.termQuery("polno", polNo))
                .query(bqb)
        ;
        return searchSourceBuilder;
    }

    public SearchRequest addSumAgg(SearchRequest searchRequest, SearchSourceBuilder searchSourceBuilder, String toGroupField,
                                   String toSumField) {
        String group = "group_" + toGroupField;
        String sum = "sum_" + toSumField;
        final TermsAggregationBuilder group_top = AggregationBuilders.terms(group).field(toSumField);
        group_top.subAggregation(AggregationBuilders.sum(sum).field(toSumField));
        searchSourceBuilder.aggregation(group_top);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    public SearchRequest addMaxAgg(SearchRequest searchRequest, SearchSourceBuilder searchSourceBuilder, String toGroupField,
                                   String toMaxField) {
        String group = "group_" + toGroupField;
        String max = "max_" + toMaxField;
        final TermsAggregationBuilder group_top = AggregationBuilders.terms(group).field(toMaxField);
        group_top.subAggregation(AggregationBuilders.max(max).field(toMaxField));
        searchSourceBuilder.aggregation(group_top);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    public double calSum(EsClient esClient, String toSumField, String bucketKey, SearchRequest searchRequest) throws Exception {
        String group = "group_" + toSumField;
        final SearchResponse searchResponse = esClient.getClient().search(searchRequest, RequestOptions.DEFAULT);
        final Aggregations aggregations = searchResponse.getAggregations();
        final Terms aggregation = aggregations.get(group);
        final Optional<Terms.Bucket> bucket = Optional.ofNullable(aggregation.getBucketByKey(bucketKey));
        if (bucket.isPresent()) {
            final Optional<Sum> sum_amnt = Optional.ofNullable(bucket.get().getAggregations().get("sum_amnt"));
            if (sum_amnt.isPresent()) {
                return sum_amnt.get().getValue();
            } else {
                return 0.0;
            }
        } else {
            return 0.0;
        }
    }

    public Object calMax(EsClient esClient, String toMaxField, String bucketKey, SearchRequest searchRequest) throws Exception {
        String group = "group_" + toMaxField;
        final SearchResponse searchResponse = esClient.getClient().search(searchRequest, RequestOptions.DEFAULT);
        final Aggregations aggregations = searchResponse.getAggregations();
        final Terms aggregation = aggregations.get(group);
        final Optional<Terms.Bucket> bucket = Optional.ofNullable(aggregation.getBucketByKey(bucketKey));
        if (bucket.isPresent()) {
            final Optional<Max> max = Optional.ofNullable(bucket.get().getAggregations().get("max"));
            if (max.isPresent()) {
                return max.get().getValue();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
