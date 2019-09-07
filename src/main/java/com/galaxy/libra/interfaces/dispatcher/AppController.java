package com.galaxy.libra.interfaces.dispatcher;

import com.galaxy.libra.app.todo.RiskInsuredAmnt;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntRequstBody;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(path = "/post", consumes = "application/json")
    public ResponseEntity<RiskAmntResponseBody> getRes(HttpEntity<RiskAmntRequstBody> riskAmntRequstBody) throws Exception {
        return new ResponseEntity<>(new RiskAmntResponseBody(riskInsuredAmnt.todo(riskAmntRequstBody), true), HttpStatus.OK);
    }

}
