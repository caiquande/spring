package com.galaxy.libra.app.dom.biz.vo.es.listener;

import com.galaxy.libra.app.dom.biz.vo.es.FutureListener;
import org.elasticsearch.action.bulk.BulkResponse;

import java.util.concurrent.Callable;

/**
 * @author caesar
 * @title
 * @description
 * @package com.sino.dev.underlier.es.listener
 * @date 2019-08-30
 * @time 09:23
 * @p_name oracleTest
 */
public class BulkInsertListener extends FutureListener<BulkResponse, Boolean> {
    public BulkInsertListener(Callable<Boolean> callable) {
        super(callable);
    }

    @Override
    public void onResponse(BulkResponse bulkItemResponses) {
        if (bulkItemResponses.hasFailures()) {
            System.out.println("error happens");
        } else {
            this.run();
        }
    }

    @Override
    public void onFailure(Exception e) {
        System.out.println(e.getMessage());
    }

    public Callable<Boolean> getCallabl() {
        final Callable<Boolean> callable = () -> {
            return true;
        };
        return callable;
    }

}
