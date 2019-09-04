package com.galaxy.libra.dom.biz.service.oracle;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import com.galaxy.libra.dom.biz.entity.client.OracleClient;
import com.galaxy.libra.dom.biz.service.DataDML;
import com.galaxy.libra.dom.biz.service.es.EsDML;
import com.galaxy.libra.dom.biz.vo.es.listener.BulkInsertListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.service.oracle
 * @date 2019-09-04
 * @time 13:04
 * @p_name spring
 */
@Component
public class OracleDMl implements DataDML {

    //todo to finish
    public void oracleHistoryToEs(int perThreadLoaded,
                                  String minIdSql,
                                  String maxIdSql,
                                  String indexName,
                                  String extractOracleSql,
                                  OracleClient oracleClient,
                                  OracleDQL oracleDQL,
                                  EsDML esDML,
                                  EsClient esClient) throws Exception {
        final Long minId = oracleDQL.getLongRes(oracleClient, minIdSql);
        final Long maxId = oracleDQL.getLongRes(oracleClient, maxIdSql);
        final ForkJoinPool pool = new ForkJoinPool(4);
        final Callable<Boolean> callable = () -> {
            final List<Map<String, Object>> extractOracle = oracleDQL.runSingleSql(oracleClient, extractOracleSql);
            final BulkInsertListener bulkInsertListener = new BulkInsertListener(BulkInsertListener.getCallabl());
            esDML.bulkInsert(esClient, indexName, extractOracle, "id", bulkInsertListener);
            return bulkInsertListener.get();
        };
        final TransportTask loadTask = new TransportTask(perThreadLoaded, minId, maxId, callable);
        pool.submit(loadTask);
        pool.awaitTermination(4, TimeUnit.SECONDS);
        pool.shutdown();
    }

}
