package com.galaxy.libra.interfaces.dispatcher;

import com.galaxy.libra.app.audit.RiskAmntSeviceAudit;
import com.galaxy.libra.app.task.RiskInsuredAmnt;
import com.galaxy.libra.dom.biz.event.RiskInsuredAmntEvent;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntRequstBody;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntResponseBody;
import com.galaxy.libra.infra.bus.Bus;
import com.galaxy.libra.infra.config.OracleClientConfig;
import com.galaxy.libra.interfaces.dto.risk.RiskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

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
    private RiskAmntSeviceAudit riskAmntSeviceAudit;

    @Autowired
    private Bus bus;

    @Autowired
    private RiskInsuredAmnt riskInsuredAmnt;

    @Autowired
    private OracleClientConfig oracleClientConfig;

    @PostConstruct
    public void init() {
        bus.getBus().register(riskAmntSeviceAudit);
    }

    @PostMapping(path = "/post", consumes = "application/json")
    public ResponseEntity<RiskAmntResponseBody> getRes(HttpEntity<RiskAmntRequstBody> riskAmntRequstBody, @Autowired RiskDto riskDto) throws Exception {
        //return new ResponseEntity<>(new RiskAmntResponseBody(riskInsuredAmnt.todo(riskAmntRequstBody), true), HttpStatus.OK);
        final RiskInsuredAmntEvent riskInsuredAmntEvent = riskDto.genAndPostEvent(riskAmntRequstBody, bus);
        return new ResponseEntity<>(new RiskAmntResponseBody(riskInsuredAmntEvent.getRes(),
                true),
                HttpStatus.OK);
    }

    @GetMapping(path = "/config")
    public ResponseEntity<String> getConfig(HttpEntity<String> httpEntity) {
        return new ResponseEntity<>(oracleClientConfig.getUser(), HttpStatus.OK);
    }

}
