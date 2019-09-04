package com.galaxy.libra.dom.biz.vo.es.listener;

import com.galaxy.libra.dom.biz.vo.es.FutureListener;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.util.concurrent.Callable;

/**
 * @author caesar
 * @title
 * @description
 * @package com.sino.dev.underlier.es.listener
 * @date 2019-08-29
 * @time 17:26
 * @p_name oracleTest
 */
public class CreateIndexListener extends FutureListener<CreateIndexResponse, Boolean> {
    private String indexName;

    public CreateIndexListener(String indexName,Callable<Boolean> callable) {
        super(callable);
        this.indexName = indexName;
    }

    @Override
    public void onResponse(CreateIndexResponse createIndexResponse) {
        if (createIndexResponse.index().equals(indexName)) {
            System.out.printf("the index s% exists", indexName);
        }else {
            this.run();
        }
    }

    @Override
    public void onFailure(Exception e) {

    }

    public Callable<Boolean> getCallabl() {
        final Callable<Boolean> callable = () -> {
            return true;
        };
        return callable;
    }
}
