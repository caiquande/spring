package com.galaxy.libra.app.audit;

import com.galaxy.libra.dom.biz.agg.es.riskinsuredamnt.RiskAmntRoot;
import com.galaxy.libra.dom.biz.event.RiskInsuredAmntEvent;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntRequstBody;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.SettableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.app.task
 * @date 2019-09-08
 * @time 15:20
 * @p_name spring
 */

@Component
public class RiskAmntSeviceAudit {

    @Autowired
    private RiskAmntRoot riskAmntRoot;

    private ArrayList<RiskInsuredAmntEvent> arrayList = new ArrayList<>();


    @Subscribe
    public void callService(RiskInsuredAmntEvent riskInsuredAmntEvent) throws Exception{
        arrayList.add(riskInsuredAmntEvent);
        while (arrayList.size()>=10) {
            System.out.println("too bizy");
            Thread.sleep(4000);
        }
        Thread.sleep(4000);
        final double res = riskAmntRoot.handleRequestEvent(riskInsuredAmntEvent);
        riskInsuredAmntEvent.setRes(res);
        synchronized (this) {
            arrayList.remove(riskInsuredAmntEvent);
        }
    }

}
