package com.galaxy.libra.dom.biz.vo.es;

import org.elasticsearch.action.ActionListener;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author caesar
 * @title
 * @description
 * @package com.sino.dev.interlayer.model.es
 * @date 2019-08-29
 * @time 15:19
 * @p_name oracleTest
 */

public abstract class FutureListener<EsResponse,CallResponse> extends FutureTask<CallResponse> implements ActionListener<EsResponse> {

    public FutureListener(Callable<CallResponse> callable){
        super(callable);
    }


    //    {
//        final FutureActionListener futureActionListener = new FutureActionListener(callable) {
//            @Override
//            public void onResponse(EsResponse r) {
//                if (r != null) {
//                    this.run();
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        };
//        return futureActionListener;
//    }

}
