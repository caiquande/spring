package com.galaxy.libra.app.dom.biz.vo.es.listener;


import com.galaxy.libra.app.dom.biz.vo.es.FutureListener;

import java.util.concurrent.Callable;

/**
 * @author caesar
 * @title
 * @description
 * @package com.sino.dev.underlier.es.listener
 * @date 2019-08-29
 * @time 16:56
 * @p_name oracleTest
 */
public class ExistIndexListener extends FutureListener<Boolean, Boolean> {

    public ExistIndexListener(Callable<Boolean> callable){
        super(callable);
    }

    @Override
    public void onResponse(Boolean aBoolean) {
        if (aBoolean) {
            System.out.println("index exists");
        }else {
            this.run();
        }
    }

    @Override
    public void onFailure(Exception e) {
        System.out.println(e.getMessage());
    }

    public static Callable<Boolean> getCallabl() {
        final Callable<Boolean> callable = () -> {
            System.out.println("index do not exist");
            return false;
        };
        return callable;
    }
}
