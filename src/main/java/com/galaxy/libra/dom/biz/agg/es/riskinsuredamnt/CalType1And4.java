package com.galaxy.libra.dom.biz.agg.es.riskinsuredamnt;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg
 * @date 2019-09-01
 * @time 16:04
 * @p_name bigdataplatform
 */
@SuppressWarnings("ALL")
@Component
public class CalType1And4 extends RiskInsuredAmntBase {

    //todo to finish
    public void mapSql(int size, EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                       String toGroupField,
                       String toSumField, double factor) throws Exception {
        if (size == 130 || size == 136) {
            Ct130136.Ct14Special0(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 190) {
            new Ct190().Ct14Special2(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        }
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    static class Ct130136 {
        //130,136
        @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
        public static double Ct14Special0(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo,
                                          String toGroupField
                , String toSumField,
                                          double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }
    }

    static class Ct159172 {
        //159,160,161,162,163,164,165,166,170,172
        public static double Ct14Special1(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo,
                                          String toGroupField, String toSumField,
                                          double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }

    }

    static class Ct190 {
        //190
        public double Ct14Special2(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField,
                                   String toSumField, double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .must(QueryBuilders.rangeQuery("appage").lt(18));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }
    }

    static class Ct331 {
        //331
        public double Ct14Special3(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField,
                                   String toSumField) throws Exception {
            int age = 0;
            final Object searchById = getEsDML().searchById(esClient, indexName, "polno", polNo, "appage", "_id");
            if (searchById != null) {
                age = Integer.valueOf(searchById.toString());
                if (age >= 18 && age <= 40) {
                    return Ct159172.Ct14Special1(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 0.6);
                } else if (age >= 41 && age <= 60) {
                    return Ct159172.Ct14Special1(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 0.4);
                } else if (age >= 61) {
                    return Ct159172.Ct14Special1(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 0.2);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
    }

    static class Ct428429 {
        //428,429
        public double Ct14Special4(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo) throws Exception {
            int age = 0;
            final Object searchById = getEsDML().searchById(esClient, indexName, "polno", polNo, "appage", "_id");
            if (searchById != null) {
                age = Integer.valueOf(searchById.toString());
                if (age < 18) {
                    return Ct159172.Ct14Special1(esClient, indexName, insuredNo, riskCode, polNo, "insuredno", "amnt", 0.5);
                } else {
                    return Ct159172.Ct14Special1(esClient, indexName, insuredNo, riskCode, polNo, "insuredno", "riskamnt", 1);
                }
            } else {
                return 0;
            }
        }
    }

    static class Ct535 {
        //535
        public double Ct14Special5(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo) throws Exception {
            final String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            final Object searchById1 = getEsDML().searchById(esClient, "ldriskrule", "_id", "idval", "add3months", "_id");
            if (searchById1 != null) {
                final String substring = searchById1.toString().substring(0, 7);
                if (substring.compareTo(yyyyMMdd) >= 0) {
                    return Ct159172.Ct14Special1(esClient, indexName, insuredNo, riskCode, polNo, "insuredno", "amnt", 1.2);
                } else {
                    return Ct159172.Ct14Special1(esClient, indexName, insuredNo, riskCode, polNo, "insuredno", "amnt", 1.4);
                }
            } else {
                return 0;
            }
        }
    }

    static class Ct685 {
        //685
        public double Ct14Special685(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField,
                                     String toSumField,
                                     int payendyear) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .mustNot(QueryBuilders.termQuery("payintv", 0.0))
                        .must(QueryBuilders.termQuery("payendyearflag", "Y"));
            };
            return runSum1Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, payendyear,
                    queryBuilderCallable);
        }

        public double Ct14Special6(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField,
                                   String toSumField, int payendyear) throws Exception {
            final double part1 = Ct159172.Ct14Special1(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 2);
            final double part2 = Ct14Special685(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 5);
            final double part3 = Ct14Special685(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 8);
            return part1 + part2 + part3;
        }
    }


}
