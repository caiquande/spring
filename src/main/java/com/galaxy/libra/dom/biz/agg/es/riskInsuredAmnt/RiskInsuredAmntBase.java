package com.galaxy.libra.dom.biz.agg.es.riskInsuredAmnt;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import com.galaxy.libra.dom.biz.entity.client.OracleClient;
import com.galaxy.libra.dom.biz.service.es.EsDML;
import com.galaxy.libra.dom.biz.service.es.EsDQL;
import com.galaxy.libra.dom.biz.service.oracle.OracleDQL;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg
 * @date 2019-09-01
 * @time 15:39
 * @p_name bigdataplatform
 */
public class RiskInsuredAmntBase {
    private static @Autowired
    EsDML esDML;
    private static @Autowired
    EsDQL esDQL;
    private static @Autowired
    EsClient esClient;
    private static @Autowired
    OracleDQL oracleDQL;
    private static @Autowired
    OracleClient oracleClient;

    public static EsClient getEsClient() {
        return esClient;
    }

    public static OracleDQL getOracleDQL() {
        return oracleDQL;
    }

    public static OracleClient getOracleClient() {
        return oracleClient;
    }

    public static EsDML getEsDML() {
        return esDML;
    }

    public static EsDQL getEsDQL() {
        return esDQL;
    }


    public static double runSum0(EsClient esClient, String indexName, String insuredNo, String toGroupField, String toSumField,
                                 double factor, Callable<BoolQueryBuilder> queryBuilderCallable) throws Exception {
        final SearchRequest searchRequest = esDQL.genSearchRequest(indexName);
        final SearchSourceBuilder searchSourceBuilder = esDQL.genSearchSourceBuilder(queryBuilderCallable.call());
        final SearchRequest searchRequest1 = esDQL.addSumAgg(searchRequest, searchSourceBuilder, toGroupField, toSumField);
        return esDQL.calSum(esClient, toSumField, insuredNo, searchRequest1) * factor;
    }

    public static double runSum0Ext(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField, String toSumField,
                                    double factor, Callable<BoolQueryBuilder> queryBuilderCallable) throws Exception {
        final SearchRequest searchRequest = esDQL.genSearchRequest(indexName);
        final SearchSourceBuilder searchSourceBuilder = esDQL.genSearchSourceBuilder(insuredNo, riskCode, polNo,
                queryBuilderCallable.call());
        final SearchRequest searchRequest1 = esDQL.addSumAgg(searchRequest, searchSourceBuilder, toGroupField, toSumField);
        return esDQL.calSum(esClient, toSumField, insuredNo, searchRequest1) * factor;
    }

    public static double runSum1Ext(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField, String toSumField,
                                    int factor, Callable<BoolQueryBuilder> queryBuilderCallable) throws Exception {
        final SearchRequest searchRequest = esDQL.genSearchRequest(indexName);
        final SearchSourceBuilder searchSourceBuilder = esDQL.genSearchSourceBuilder(insuredNo, riskCode, polNo,
                queryBuilderCallable.call());
        final SearchRequest searchRequest1 = esDQL.addSumAgg(searchRequest, searchSourceBuilder, toGroupField, toSumField);
        return esDQL.calSum(esClient, toSumField, insuredNo, searchRequest1) * factor;
    }

    public static Object runMax0(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField, String toMaxField,
                                 Callable<BoolQueryBuilder> queryBuilderCallable) throws Exception {
        final SearchRequest searchRequest = esDQL.genSearchRequest(indexName);
        final SearchSourceBuilder searchSourceBuilder = esDQL.genSearchSourceBuilder(insuredNo, riskCode, polNo,
                queryBuilderCallable.call());
        final SearchRequest searchRequest1 = esDQL.addMaxAgg(searchRequest, searchSourceBuilder, toGroupField, toMaxField);
        return esDQL.calMax(esClient, toMaxField, insuredNo, searchRequest1);
    }

}
