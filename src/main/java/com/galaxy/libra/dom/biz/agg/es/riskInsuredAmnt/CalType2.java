package com.galaxy.libra.dom.biz.agg.es.riskInsuredAmnt;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg.es.riskAmnt
 * @date 2019-09-01
 * @time 16:10
 * @p_name bigdataplatform
 */
@Component
public class CalType2 extends RiskInsuredAmntBase {

    //todo to finish
    public void mapSql(int size, EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                       String toGroupField,
                       String toSumField, double factor,double adder,int years) throws Exception{
        if (size==147||size==149||size==152||size==154||size==155)
        {
            new CT147155().CT2Special0(esClient,indexName,insuredNo,riskCode,polNo,toGroupField,toSumField,factor,adder);
        } else if (size == 159 || size == 160 || size == 161 || size == 163 || size == 164) {
            new CT170172().CT2Special1(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, years);
        }

    }

    static class CT147155 {
        //147,149,152,154,155
        public double CT2Special0(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField, String toSumField,
                                  double factor, double adder) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4));
            };
            final SearchRequest searchRequest = getEsDQL().genSearchRequest(indexName);
            final SearchSourceBuilder searchSourceBuilder = getEsDQL().genSearchSourceBuilder(queryBuilderCallable.call());
            final SearchRequest searchRequest1 = getEsDQL().addSumAgg(searchRequest, searchSourceBuilder, toGroupField,
                    toSumField);
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable) + adder;
        }
    }

    //159,160,161,162,163,164,168 equals CT14Special1

    static class CT170172 {
        //170,172
        public double CT2Special1(EsClient esClient, String indexName, String insuredNo,
                                  String riskCode, String polNo, String toGroupField, String toSumField,
                                  double factor, int payyears) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9));
            };
            final SearchRequest searchRequest = getEsDQL().genSearchRequest(indexName);
            final SearchSourceBuilder searchSourceBuilder = getEsDQL().genSearchSourceBuilder(queryBuilderCallable.call());
            final SearchRequest searchRequest1 = getEsDQL().addSumAgg(searchRequest, searchSourceBuilder, toGroupField,
                    toSumField);
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable) * payyears;
        }
    }

    static class CT449 {
        //449
        public double CT2Special449P1(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField, String toSumField,
                                      double factor) throws Exception {
            int[] payendyears = new int[]{10, 15, 20};
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .must(QueryBuilders.termsQuery("payendyear", payendyears));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }

        public double CT2Special449P2(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField, String toSumField,
                                      double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .must(QueryBuilders.boolQuery()
                                .should(QueryBuilders.boolQuery()
                                        .must(QueryBuilders.termQuery("payendyear", 5))
                                        .mustNot(QueryBuilders.termQuery("payintv", 0)))
                                .should(QueryBuilders.termQuery("payintv", 0)));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }

        public double CT2Special2(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField, String toSumField, double factor) throws Exception {
            return CT2Special449P1(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 3) +
                    CT2Special449P2(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 1);
        }
    }

    static class CT482 {
        //482
        //todo modify the method body
        public static double callCalaccval(String contno) {
            return 0.0;
        }

        public static String selectEdorno(EsClient esClient, String contno) throws Exception {
            final String[] strings = {"1", "2", "3", "5", "6", "a"};
            final SearchRequest searchRequest = new SearchRequest("lpedoritem")
                    .source(new SearchSourceBuilder()
                            .query(QueryBuilders.boolQuery()
                                    .must(QueryBuilders.termQuery("edortype", "IA"))
                                    .must(QueryBuilders.boolQuery()
                                            .should(QueryBuilders.boolQuery()
                                                    .must(QueryBuilders.termQuery("edorstate", "0"))
                                                    .must(QueryBuilders.termQuery("standbyflag1", "N")))
                                            .should(QueryBuilders.termsQuery("edorstate", strings)))
                                    .must(QueryBuilders.termQuery("contno", contno))
                            )
                    );
            final SearchResponse response = esClient.getClient().search(searchRequest,
                    RequestOptions.DEFAULT);
            final Optional<SearchResponse> response1 = Optional.ofNullable(response);
            if (response1.isPresent()) {
                final SearchHit[] hits = response1.get().getHits().getHits();
                if (hits.length > 0) {
                    return hits[0].getSourceAsMap().get("edorno").toString();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

        public double CT2Special3(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo, String toGroupField, String toSumField,
                                  double factor) throws Exception {
            final String edorno = selectEdorno(esClient, contno);
            Callable<BoolQueryBuilder> queryBuilderCallable3 = null;
            double res = 0.0;
            if (edorno != null) {
                queryBuilderCallable3 = () -> {
                    return QueryBuilders.boolQuery()
                            .must(QueryBuilders.termQuery("edortype", "IA"))
                            .must(QueryBuilders.termQuery("PolNo", polNo))
                            .must(QueryBuilders.termQuery("edorno", edorno));
                };
                final SearchRequest searchRequest = getEsDQL().genSearchRequest(indexName);
                final SearchSourceBuilder searchSourceBuilder =
                        getEsDQL().genSearchSourceBuilder(queryBuilderCallable3.call());
                final SearchRequest searchRequest1 = getEsDQL().addSumAgg(searchRequest, searchSourceBuilder, toGroupField,
                        toSumField);
                final double part3 = getEsDQL().calSum(esClient, toSumField, insuredNo, searchRequest1);
                final Callable<BoolQueryBuilder> queryBuilderCallable2 = () -> {
                    return QueryBuilders.boolQuery()
                            .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                            .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                            .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                            .mustNot(QueryBuilders.termQuery("appflag", 4))
                            .mustNot(QueryBuilders.termQuery("appflag", 9));
                };
                final SearchRequest searchRequest2 = getEsDQL().genSearchRequest(indexName);
                final SearchSourceBuilder searchSourceBuilder1 =
                        getEsDQL().genSearchSourceBuilder(queryBuilderCallable2.call());
                final SearchRequest searchRequest3 = getEsDQL().addSumAgg(searchRequest2, searchSourceBuilder1, toGroupField,
                        toSumField);
                final double part2 = getEsDQL().calSum(esClient, "insuredno", insuredNo, searchRequest3);
                final double part1 = callCalaccval(contno);
                double maxP23 = 0;
                double max = 0;
                if (part3 >= part2) {
                    maxP23 = part3;
                } else {
                    maxP23 = part2;
                }
                if (part1 * 1.05 >= maxP23) {
                    max = part1 * 1.05;
                } else {
                    max = maxP23;
                }
                res = max - part1;
            }
            return new BigDecimal(res).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    static class CT642 {
        //642
        // todo calMax date format need to test
        public double CT2Special4(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo, String toGroupField, String toSumField,
                                  double factor) throws Exception {
            double part0 = 0.0;
            double part3 = 0.0;
            final Callable<BoolQueryBuilder> queryBuilderCallable1 = () -> {
                return QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("insuredno", indexName))
                        .must(QueryBuilders.termQuery("polno", polNo))
                        .must(QueryBuilders.termQuery("riskcode", riskCode));
            };
            final SearchRequest searchRequest1 = getEsDQL().genSearchRequest("LCAmntChg");
            final SearchSourceBuilder searchSourceBuilder1 = getEsDQL().genSearchSourceBuilder(queryBuilderCallable1.call());
            final SearchRequest searchRequest10 = getEsDQL().addMaxAgg(searchRequest1, searchSourceBuilder1, toGroupField,
                    toSumField);
            final Object calMax = getEsDQL().calMax(esClient, toSumField, insuredNo, searchRequest10);
            final String string = calMax.toString();
            final Callable<BoolQueryBuilder> queryBuilderCallable2 = () -> {
                return QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("polno", polNo))
                        .must(QueryBuilders.termQuery("insuredno", indexName))
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9));
            };
            boolean exists = false;
            final SearchRequest searchRequest2 = new SearchRequest("LCAmntChg").source(new SearchSourceBuilder().query(queryBuilderCallable2.call()));
            final SearchResponse searchResponse = esClient.getClient().search(searchRequest2,
                    RequestOptions.DEFAULT);
            final Optional<SearchResponse> searchResponse1 = Optional.ofNullable(searchResponse);
            if (searchResponse1.isPresent()) {
                final SearchHit[] hits = searchResponse1.get().getHits().getHits();
                if (hits.length > 0) {
                    exists = true;
                }
            }
            if (exists) {
                final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                    return QueryBuilders.boolQuery().must(QueryBuilders.termQuery("begindate", string));
                };
                final SearchRequest searchRequest0 = getEsDQL().genSearchRequest("LCAmntChg");
                final SearchSourceBuilder searchSourceBuilder0 =
                        getEsDQL().genSearchSourceBuilder(queryBuilderCallable.call());
                final SearchRequest searchRequest00 = getEsDQL().addSumAgg(searchRequest0, searchSourceBuilder0,
                        toGroupField,
                        toSumField);
                part0 = getEsDQL().calSum(esClient, toSumField, insuredNo, searchRequest00);
            }
            final Callable<BoolQueryBuilder> queryBuilderCallable3 = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .mustNot(QueryBuilders.termQuery("appflag", 1));
            };

            final SearchRequest searchRequest3 = getEsDQL().genSearchRequest(indexName);
            final SearchSourceBuilder searchSourceBuilder3 = getEsDQL().genSearchSourceBuilder(queryBuilderCallable3.call());
            final SearchRequest searchRequest30 = getEsDQL().addSumAgg(searchRequest3, searchSourceBuilder3, toGroupField,
                    toSumField);
            part3 = getEsDQL().calSum(esClient, toSumField, insuredNo, searchRequest30) * factor;
            return part0 + part3;
        }
    }

    static class CT10031007 {
        //1003,1007
        public double CT2Special5(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                  String toGroupField,
                                  String toSumField, double factor) throws Exception {
            double part1 = 0.0;
            double part2 = 0.0;
            double part3 = 0.0;
            double part4 = 0.0;
            double maxp12 = 0.0;
            double maxp34 = 0.0;
            final Callable<BoolQueryBuilder> queryBuilderCallable1 =
                    () -> {
                        return QueryBuilders.boolQuery()
                                .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                                .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                                .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                                .mustNot(QueryBuilders.termQuery("appflag", 4))
                                .mustNot(QueryBuilders.termQuery("appflag", 9));
                    };
            part1 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable1);
            final String edorno = CT482.selectEdorno(esClient, contno);
            final Callable<BoolQueryBuilder> queryBuilderCallable2 = () -> {
                return QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("edortype", "IA"))
                        .must(QueryBuilders.termQuery("polno", polNo))
                        .must(QueryBuilders.termQuery("edorno", edorno));
            };
            part2 = runSum0(esClient, "lppol", insuredNo, toGroupField, toSumField, 1.0, queryBuilderCallable2);
            final Callable<BoolQueryBuilder> queryBuilderCallable3 =
                    () -> {
                        return QueryBuilders.boolQuery()
                                .must(QueryBuilders.termQuery("insuredno", indexName))
                                .must(QueryBuilders.termQuery("mainpolno", polNo))
                                .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                                .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                                .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                                .mustNot(QueryBuilders.termQuery("appflag", 4))
                                .mustNot(QueryBuilders.termQuery("appflag", 9));
                    };
            part3 = runSum0(esClient, indexName, insuredNo, toGroupField, toSumField, 1.0, queryBuilderCallable3);
            final Callable<BoolQueryBuilder> queryBuilderCallable4 = () -> {
                return QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("edortype", "IA"))
                        .must(QueryBuilders.termQuery("comparePandMP", 0))
                        .must(QueryBuilders.termQuery("edorno", edorno));
            };
            part4 = runSum0(esClient, "lppol", insuredNo, toGroupField, toSumField, 1.0, queryBuilderCallable4);
            if (part1 >= part2) {
                maxp12 = part1;
            } else {
                maxp12 = part2;
            }
            if (part3 >= part4) {
                maxp34 = part3;
            } else {
                maxp34 = part4;
            }
            final double val = maxp12 - maxp12 / maxp34 * CT482.callCalaccval(contno);
            return new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * -1;
        }


    }


}
