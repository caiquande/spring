package com.galaxy.libra.interfaces.dispatcher;

import com.galaxy.libra.app.todo.RiskInsuredAmnt;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntRequstBody;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntResponseBody;
import com.galaxy.libra.infra.config.OracleClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.interfaces.dispatch
 * @date 2019-09-03
 * @time 18:06
 * @p_name spring
 */

@RestController
@RequestMapping("/test")
public class AppController {

    @Autowired
    private RiskInsuredAmnt riskInsuredAmnt;

    @Autowired
    private OracleClientConfig oracleClientConfig;

    @PostMapping(path = "/post", consumes = "application/json")
    public ResponseEntity<RiskAmntResponseBody> getRes(HttpEntity<RiskAmntRequstBody> riskAmntRequstBody) throws Exception {
        return new ResponseEntity<>(new RiskAmntResponseBody(riskInsuredAmnt.todo(riskAmntRequstBody), true), HttpStatus.OK);
    }

    @GetMapping(path = "/config")
    public ResponseEntity<String> getConfig(HttpEntity<String> httpEntity) {
        return new ResponseEntity<>(oracleClientConfig.getUser(), HttpStatus.OK);
    }

}
