package com.galaxy.libra.interfaces.dispatch;

import com.galaxy.libra.dom.biz.vo.http.RiskAmntRequstBody;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntResponseBody;
import org.apache.http.impl.execchain.TunnelRefusedException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/get", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<RiskAmntResponseBody> getRes(HttpEntity<RiskAmntRequstBody> riskAmntRequstBody) {
        final double val = Double.valueOf(riskAmntRequstBody.getBody().getPolNo()) + Double.valueOf(riskAmntRequstBody.getBody().getOther());
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("test-header","bar");
        return new ResponseEntity<RiskAmntResponseBody>(new RiskAmntResponseBody(val, true), httpHeaders, HttpStatus.OK);
    }

}
