package com.galaxy.libra.dom.biz.vo.es.listener;

import com.galaxy.libra.dom.biz.vo.es.FutureListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexResponse;

import java.util.concurrent.Callable;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.vo.es.listener
 * @date 2019-09-01
 * @time 14:52
 * @p_name bigdataplatform
 */
public class SingleInsertListener extends FutureListener<IndexResponse, Boolean> {
    public SingleInsertListener(Callable<Boolean> callable) {
        super(callable);
    }

    @Override
    public void onResponse(IndexResponse indexResponse) {
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            this.run();
        } else {
            System.out.println("insert not happen");
        }
    }

    @Override
    public void onFailure(Exception e) {
        System.out.println(e.getMessage());
    }
}
