package com.galaxy.libra.dom.biz.service.oracle;

import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveAction;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg.es.riskInsuredAmnt
 * @date 2019-09-03
 * @time 15:56
 * @p_name spring
 */

public class TransportTask extends RecursiveAction {
    private static final long serialVersionUID = 1L;
    private int perThreadWorkLoaded;
    private Callable<Boolean> callable;
    private Long start;
    private Long end;

    public TransportTask(int perThreadWorkLoaded, Long start, Long end, Callable<Boolean> callable) {
        this.perThreadWorkLoaded = perThreadWorkLoaded;
        this.start = start;
        this.end = end;
        this.callable = callable;
    }

    @Override
    protected void compute() {
        if (end - start <= perThreadWorkLoaded) {
            try {
                callable.call();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            final Long middle = (end + start) / 2;
            final TransportTask loadTask0 = new TransportTask(perThreadWorkLoaded, start, middle, callable);
            final TransportTask loadTask1 = new TransportTask(perThreadWorkLoaded, middle, end, callable);
            invokeAll(loadTask0, loadTask1);
        }
    }
}
