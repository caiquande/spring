package com.galaxy.libra.app.todo;

import com.galaxy.libra.dom.biz.agg.es.riskinsuredamnt.Root;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntRequstBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.app.todo
 * @date 2019-09-04
 * @time 13:54
 * @p_name spring
 */
@Component
public class RiskInsuredAmnt {
    @Autowired
    private Root root;

    public Double todo(HttpEntity<RiskAmntRequstBody> requstBodyHttpEntity) throws Exception {
        final RiskAmntRequstBody body = requstBodyHttpEntity.getBody();
        String indexName = "test";
        return root.handleRequestTest(indexName, body.getContno(), body.getInsuredNo(), body.getRiskCode(), body.getPolNo(), "group", "sum", 1.0);
    }

}
