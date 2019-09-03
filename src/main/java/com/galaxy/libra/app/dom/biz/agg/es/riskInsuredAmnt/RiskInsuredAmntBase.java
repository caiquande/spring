package com.galaxy.libra.app.dom.biz.agg.es.riskInsuredAmnt;

import com.galaxy.libra.app.dom.biz.service.es.EsDML;
import com.galaxy.libra.app.dom.biz.service.es.EsDQL;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

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
    private static  @Autowired
    EsDML esDML;
    private static  @Autowired
    EsDQL esDQL;

    public static EsDML getEsDML() {
        return esDML;
    }

    public static EsDQL getEsDQL() {
        return esDQL;
    }

    public static double runSum0(String indexName, String insuredNo, String toGroupField, String toSumField,
                          double factor, Callable<BoolQueryBuilder> queryBuilderCallable) throws Exception {
        final SearchRequest searchRequest = esDQL.genSearchRequest(indexName);
        final SearchSourceBuilder searchSourceBuilder = esDQL.genSearchSourceBuilder(queryBuilderCallable.call());
        final SearchRequest searchRequest1 = esDQL.addSumAgg(searchRequest, searchSourceBuilder, toGroupField, toSumField);
        return esDQL.calSum(toSumField, insuredNo, searchRequest1) * factor;
    }

    public static double runSum0Ext(String indexName, String insuredNo, String riskCode, String polNo, String toGroupField, String toSumField,
                             double factor, Callable<BoolQueryBuilder> queryBuilderCallable) throws Exception {
        final SearchRequest searchRequest = esDQL.genSearchRequest(indexName);
        final SearchSourceBuilder searchSourceBuilder = esDQL.genSearchSourceBuilder(insuredNo, riskCode, polNo,
                queryBuilderCallable.call());
        final SearchRequest searchRequest1 = esDQL.addSumAgg(searchRequest, searchSourceBuilder, toGroupField, toSumField);
        return esDQL.calSum(toSumField, insuredNo, searchRequest1) * factor;
    }

    public static double runSum1Ext(String indexName, String insuredNo, String riskCode, String polNo, String toGroupField, String toSumField,
                             int factor, Callable<BoolQueryBuilder> queryBuilderCallable) throws Exception {
        final SearchRequest searchRequest = esDQL.genSearchRequest(indexName);
        final SearchSourceBuilder searchSourceBuilder = esDQL.genSearchSourceBuilder(insuredNo, riskCode, polNo,
                queryBuilderCallable.call());
        final SearchRequest searchRequest1 = esDQL.addSumAgg(searchRequest, searchSourceBuilder, toGroupField, toSumField);
        return esDQL.calSum(toSumField, insuredNo, searchRequest1) * factor;
    }

    public static Object runMax0(String indexName, String insuredNo, String riskCode, String polNo, String toGroupField, String toMaxField,
                          Callable<BoolQueryBuilder> queryBuilderCallable) throws Exception {
        final SearchRequest searchRequest = esDQL.genSearchRequest(indexName);
        final SearchSourceBuilder searchSourceBuilder = esDQL.genSearchSourceBuilder(insuredNo, riskCode, polNo,
                queryBuilderCallable.call());
        final SearchRequest searchRequest1 = esDQL.addMaxAgg(searchRequest, searchSourceBuilder, toGroupField, toMaxField);
        return esDQL.calMax(toMaxField, insuredNo, searchRequest1);
    }

}
