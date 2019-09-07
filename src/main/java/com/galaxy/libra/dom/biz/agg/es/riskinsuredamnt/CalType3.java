package com.galaxy.libra.dom.biz.agg.es.riskinsuredamnt;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import com.galaxy.libra.dom.biz.vo.es.model.TwoFields0;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.aggbiz.es.riskAmnt
 * @date 2019-09-01
 * @time 18:47
 * @p_name bigdataplatform
 */
@Component
public class CalType3 extends RiskInsuredAmntBase {

    /**
     * callType 3
     */

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
        final SearchResponse response = esClient.getClient().search(searchRequest, RequestOptions.DEFAULT);
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

    //todo modify
    public double mapSql(int size, EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                         String toGroupField,
                         String toSumField, double factor) throws Exception {
        if (size == 146) {
            return new Ct146().Ct3Special0();
        } else if (size == 148) {
            return new Ct148().Ct3Special1(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 156) {
            return new Ct156().Ct3Special2(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 159 || size == 182) {
            return new Ct159182().Ct3Special3(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 186) {
            return new Ct186().Ct3Special6(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 233) {
            return new Ct233().Ct3Special7(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 281) {
            return new Ct281().Ct3Special8(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 335 || size == 407) {
            return new Ct335407().Ct3Special9(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 357) {
            return new Ct357().Ct3Special10(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 409 || size == 414) {
            return new Ct409414().Ct3Special11(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 423) {
            return new Ct423().Ct3Special12(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 428) {
            return new Ct428().Ct3Special13(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 449) {
            return new Ct449().Ct3Special14(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 455) {
            return new Ct455().Ct3Special15(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 463) {
            //return new Ct463().Ct3Special16(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
            return 0.0;
        } else if (size == 535 || size == 578) {
            return new Ct535578().Ct2Special17(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 577) {
            return new Ct577().Ct3Special18(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 642) {
            return new Ct642().Ct3Special19(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else if (size == 969) {
            return new Ct969().Ct3Special20(esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField, toSumField, factor);
        } else {
            return 0.0;
        }

    }

    class Ct146 {
        //146
        public double Ct3Special0() {
            return 70000.0;
        }
    }

    class Ct148 {
        //148
        public double Ct3Special1(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                  String toGroupField,
                                  String toSumField, double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .must(QueryBuilders.termQuery("appflag", "1"));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }
    }

    class Ct156 {
        //156
        public double Ct3Special2(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                  String toGroupField,
                                  String toSumField, double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable) + 70000.0;
        }
    }

    class Ct159182 {
        //159,160,161,162,163,164,165,166,167,168,170,172,173,175,176,179,180,182
        public double Ct3Special3(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                  String toGroupField,
                                  String toSumField, double factor) throws Exception {
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

    class Ct181206 {
        //181,206
        public double Ct3Special4(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                  String toGroupField,
                                  String toSumField, double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .must(QueryBuilders.rangeQuery("appage").gte(18));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }
    }

    class Ct {
        public double Ct3Special5a(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                   String toGroupField,
                                   String toSumField, double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .must(QueryBuilders.rangeQuery("appage").gte(18));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }

        public double Ct3Special5b(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                   String toGroupField,
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
            final SearchRequest searchRequest = new SearchRequest(indexName).source(getEsDQL().genSearchSourceBuilder(insuredNo, riskCode, polNo,
                    queryBuilderCallable.call()));
            final SearchResponse response = esClient.getClient().search(searchRequest, RequestOptions.DEFAULT);
            final Optional<SearchResponse> response1 = Optional.ofNullable(response);
            double res = 0.0;
            if (response1.isPresent()) {
                final SearchHit[] hits = response1.get().getHits().getHits();
                if (hits.length > 0) {
                    return Double.valueOf(hits[0].getSourceAsMap().get("amnt").toString());
                }
                ;
            }
            ;
            return res;
        }
    }

    class Ct186 {
        //186
        public double Ct3Special6(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                  String toGroupField,
                                  String toSumField, double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .must(QueryBuilders.rangeQuery("appage").lte(18));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }
    }

    class Ct233 {
        //233
        public double Ct3Special7(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                  String toGroupField,
                                  String toSumField, double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .must(QueryBuilders.termQuery("dutycode", "D00225"))
                        ;
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }
    }

    class Ct281 {
        //281
        public double Ct3Special8(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                  String toGroupField,
                                  String toSumField, double factor) throws Exception {
            final String[] codes = {"D00226", "D00227", "D00228", "D00230", "D00231", "D00232"};
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .must(QueryBuilders.termsQuery("dutycode", codes))
                        ;
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
        }
    }

    class Ct335407 {
        //335,336,407
        public double Ct3Special9(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                  String toGroupField,
                                  String toSumField, double factor) throws Exception {
            double part1 = 0.0;
            double part2 = 0.0;
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        ;
            };
            part1 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
            final Callable<BoolQueryBuilder> queryBuilderCallable1 = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", 4))
                        .mustNot(QueryBuilders.termQuery("appflag", 9))
                        .must(QueryBuilders.termQuery("insuredno", insuredNo))
                        .must(QueryBuilders.termQuery("contno", contno))
                        ;
            };
            part2 = runSum0(esClient, indexName, insuredNo, toGroupField, toSumField, factor, queryBuilderCallable1);
            if (part1 >= part2) {
                return part1;
            } else {
                return part2;
            }
        }
    }

    class Ct357 {
        //357
        public double Ct3Special10(EsClient esClient, String indexName, String insuredNo, String riskCode, String polNo, String toGroupField,
                                   String toSumField, double factor) throws Exception {
            double part1 = 0.0;
            double part2 = 0.0;
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .must(QueryBuilders.termQuery("appflag", "0"))
                        ;
            };
            part1 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
            final Callable<BoolQueryBuilder> queryBuilderCallable1 = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"))
                        .mustNot(QueryBuilders.termQuery("appflag", "0"))
                        ;
            };
            part2 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
            if (part1 >= part2) {
                return part1;
            } else {
                return part2;
            }
        }
    }

    class Ct409414 {
        //409,413,414
        public double Ct3Special11(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                   String toGroupField,
                                   String toSumField, double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"))
                        .must(QueryBuilders.rangeQuery("appage").gte(18));
            };
            return runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);

        }
    }

    class Ct423 {
        //423
        public double Ct3Special12(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                   String toGroupField,
                                   String toSumField, double factor) throws Exception {
            double part1 = 0.0;
            double part2 = 0.0;
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"))
                        .must(QueryBuilders.rangeQuery("appage").lt(18));
            };
            part1 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable);
            final Callable<BoolQueryBuilder> queryBuilderCallable1 = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"))
                        .must(QueryBuilders.rangeQuery("appage").lt(18));
            };
            part2 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable1);
            return part1 + part2;
        }
    }

    class Ct428 {
        //428
        public double Ct3Special13(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                   String toGroupField,
                                   String toSumField, double factor) throws Exception {
            double part1 = 0.0;
            double part2 = 0.0;
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"))
                        .must(QueryBuilders.rangeQuery("appage").gte(18))
                        .must(QueryBuilders.rangeQuery("appage").lte(60));
            };
            part1 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 2, queryBuilderCallable);
            final Callable<BoolQueryBuilder> queryBuilderCallable1 = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"))
                        .must(QueryBuilders.rangeQuery("appage").gt(60));
            };
            part2 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor, queryBuilderCallable1);
            return part1 + part2;
        }
    }

    class Ct449 {
        //449
        public double Ct3Special14(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                   String toGroupField,
                                   String toSumField, double factor) throws Exception {
            double part1 = 0.0;
            double part2 = 0.0;
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"))
                        .must(QueryBuilders.rangeQuery("appage").lt(18));
            };
            part1 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 1, queryBuilderCallable);
            final Callable<BoolQueryBuilder> queryBuilderCallable1 = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"))
                        .must(QueryBuilders.rangeQuery("appage").gte(18));
            };
            part2 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 2, queryBuilderCallable1);
            return part1 + part2;
        }
    }

    class Ct455 {
        //455
        public double Ct3Special15(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                   String toGroupField,
                                   String toSumField, double factor) throws Exception {
            double part1 = 0.0;
            double part2 = 0.0;
            final String edorno = selectEdorno(esClient, contno);
            Callable<BoolQueryBuilder> queryBuilderCallable2 = () -> {
                return QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("edortype", "IA"))
                        .must(QueryBuilders.termQuery("PolNo", polNo))
                        .must(QueryBuilders.termQuery("edorno", edorno));
            };
            part2 = runSum0(esClient, indexName, insuredNo, toGroupField, toSumField, 1, queryBuilderCallable2);
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"));
            };
            part1 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, 1, queryBuilderCallable);
            if (part1 >= part2) {
                return part1;
            } else {
                return part2;
            }
        }
    }

    @SuppressWarnings("AliEqualsAvoidNull")
    class Ct463 {
        //463
        public List<Double> Ct3Special16(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                         String toGroupField,
                                         String toSumField, double factor) throws Exception {
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"))
                        .must(QueryBuilders.termQuery("contno", contno))
                        .must(QueryBuilders.termQuery("pcomparemp", 0));
            };
            final List<Object> objects = getEsDML().searchByRequest(esClient, new SearchRequest(indexName)
                            .source(new SearchSourceBuilder().query(queryBuilderCallable.call())),
                    new TwoFields0("amntpayyears", "payintv"), 0);
            final ArrayList<Double> res = new ArrayList<>();
            if (objects != null&&objects.size()>=1) {
                for (Object o : objects) {
                    if (o != null) {
                        if (o.toString().equals("1")) {
                            res.add(12 * 0.6 * Double.valueOf(o.toString()));
                        } else if (o.toString().equals("12")) {
                            res.add(0.6 * Double.valueOf(o.toString()));
                        }
                    }
                }
            }
            return res;
        }
    }

    class Ct535578 {
        //535,578
        public double Ct2Special17(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                   String toGroupField,
                                   String toSumField,
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
                final double part3 = runSum0(esClient, indexName, insuredNo, toGroupField, toSumField, 1, queryBuilderCallable3);
                final Callable<BoolQueryBuilder> queryBuilderCallable2 = () -> {
                    return QueryBuilders.boolQuery()
                            .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                            .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                            .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                            .mustNot(QueryBuilders.termQuery("appflag", 4))
                            .mustNot(QueryBuilders.termQuery("appflag", 9));
                };
                final double part2 = runSum0Ext(esClient, indexName, insuredNo, riskCode, polNo, toGroupField, toSumField, factor,
                        queryBuilderCallable2);
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

    class Ct577 {
        //577
        public double Ct3Special18(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                   String toGroupField,
                                   String toSumField, double factor) throws Exception {
            double part1 = 0.0;
            final Callable<BoolQueryBuilder> queryBuilderCallable = () -> {
                return QueryBuilders.boolQuery()
                        .mustNot(QueryBuilders.termQuery("uwflag", "1"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "2"))
                        .mustNot(QueryBuilders.termQuery("uwflag", "a"))
                        .mustNot(QueryBuilders.termQuery("appflag", "4"))
                        .mustNot(QueryBuilders.termQuery("appflag", "9"))
                        .must(QueryBuilders.termQuery("insuredno", insuredNo))
                        .must(QueryBuilders.termQuery("contno", contno));
            };
            part1 = runSum0(esClient, indexName, insuredNo, toGroupField, toSumField, 1, queryBuilderCallable);
            final SearchRequest searchRequest = new SearchRequest(indexName)
                    .source(new SearchSourceBuilder()
                            .query(QueryBuilders.termQuery("rownum", 1))
                            .query(QueryBuilders.termQuery("insuredno", insuredNo))
                            .query(QueryBuilders.termQuery("contno", contno)));
            final List<Object> byRequest = getEsDML().searchByRequest(esClient, searchRequest, new TwoFields0("contno", "insuyear"), 1);
            if (byRequest.get(0).toString().equals("20")) {
                return part1 * 0.15;
            } else {
                return part1 * 0.25;
            }
        }
    }

    class Ct642 {
        //642
        public double Ct3Special19(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
                                   String toGroupField,
                                   String toSumField,
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
            final SearchRequest searchRequest10 = getEsDQL().addMaxAgg(searchRequest1, searchSourceBuilder1, toGroupField, toSumField);
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
            final SearchResponse searchResponse = esClient.getClient().search(searchRequest2, RequestOptions.DEFAULT);
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
                final SearchSourceBuilder searchSourceBuilder0 = getEsDQL().genSearchSourceBuilder(queryBuilderCallable.call());
                final SearchRequest searchRequest00 = getEsDQL().addSumAgg(searchRequest0, searchSourceBuilder0, toGroupField, toSumField);
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
            final SearchRequest searchRequest30 = getEsDQL().addSumAgg(searchRequest3, searchSourceBuilder3, toGroupField, toSumField);
            part3 = getEsDQL().calSum(esClient, toSumField, insuredNo, searchRequest30) * factor;
            return part0 + part3;
        }
    }

    class Ct969 {
        //969
        public double Ct3Special20(EsClient esClient, String indexName, String contno, String insuredNo, String riskCode, String polNo,
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
            final String edorno = selectEdorno(esClient, contno);
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
            final double val = maxp12 - maxp12 / maxp34 * callCalaccval(contno);
            return new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * -1;
        }
    }


}
