package com.galaxy.libra.dom.biz.vo.es.listener;

import com.galaxy.libra.dom.biz.vo.es.FutureListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.util.concurrent.Callable;

/**
 * @author caesar
 * @title
 * @description
 * @package com.sino.dev.underlier.es.listener
 * @date 2019-08-30
 * @time 09:10
 * @p_name oracleTest
 */
public class UpdateSingleDocListener extends FutureListener<UpdateResponse, Boolean> {
    public UpdateSingleDocListener(Callable<Boolean> callable) {
        super(callable);
    }

    @Override
    public void onResponse(UpdateResponse updateResponse) {
        if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            this.run();
        } else {
            System.out.println("no update happened");
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
