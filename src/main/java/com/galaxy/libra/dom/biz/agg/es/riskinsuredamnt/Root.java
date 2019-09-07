package com.galaxy.libra.dom.biz.agg.es.riskinsuredamnt;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import com.galaxy.libra.dom.biz.entity.client.OracleClient;
import com.galaxy.libra.dom.biz.service.oracle.OracleDQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg.es.riskInsuredAmnt
 * @date 2019-09-03
 * @time 14:48
 * @p_name spring
 */
@Component
public class Root {
    @Autowired
    private CalType1And4 calType1And4;
    @Autowired
    private CalType2 calType2;
    @Autowired
    private CalType3 calType3;
    public double handleRequest(String indexName, String contno, String insuredNo, String riskCode, String polNo, String toGroupField,
                                String toSumField, double factor) throws Exception{
        final OracleDQL oracleDQL = CalType3.getOracleDQL();
        final OracleClient oracleClient = CalType3.getOracleClient();
        final EsClient esClient = CalType3.getEsClient();
        final String getSql = oracleDQL.getStringRes(oracleClient,"getSql");
        final int length = getSql.toCharArray().length;
        return calType3.mapSql(length, esClient, indexName, contno, insuredNo, riskCode, polNo, toGroupField,
                toSumField, factor);
    }

    public double handleRequestTest(String indexName, String contno, String insuredNo, String riskCode, String polNo, String toGroupField,
                                String toSumField, double factor) throws Exception{
        return 1.0;
    }

}
