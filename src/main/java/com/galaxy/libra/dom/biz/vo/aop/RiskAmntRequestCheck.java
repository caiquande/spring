package com.galaxy.libra.dom.biz.vo.aop;

import com.galaxy.libra.dom.biz.vo.http.RiskAmntRequstBody;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntResponseBody;
import com.galaxy.libra.dom.biz.vo.http.filter.RiskAmntHttpHeaderCheck;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.vo.http.filter
 * @date 2019-09-04
 * @time 10:35
 * @p_name spring
 */
@Aspect
@Component
public class RiskAmntRequestCheck {

    @Autowired
    RiskAmntHttpHeaderCheck riskAmntHttpHeaderCheck;

    @Pointcut("call(com.galaxy.libra.interfaces.dispatch.getRes(HttpEntity<RiskAmntRequstBody>))&&args(he)")
    public void check(HttpEntity<RiskAmntRequstBody> he) {
    }

    @Around("check(he)")
    public void doCheck(ProceedingJoinPoint joinPoint, HttpEntity<RiskAmntRequstBody> he) {
        ResponseEntity<RiskAmntResponseBody> res;
        final HttpHeaders headers = he.getHeaders();
        final Optional<Boolean> aBoolean = Optional.of(headers.containsKey("test-header"));
        if (aBoolean.isPresent()) {
            final String s = headers.get("test-header").get(0);
            if (s.equals("test-header")) {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    System.out.println(throwable.getMessage());
                    returnWrong();
                }
            }else {
                returnWrong();
            }
        }else {
            returnWrong();
        }
    }

    public ResponseEntity<String> returnWrong() {
        return new ResponseEntity<String>("bad request", HttpStatus.BAD_REQUEST);
    }

}
