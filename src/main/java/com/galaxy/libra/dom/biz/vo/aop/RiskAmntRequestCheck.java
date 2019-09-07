package com.galaxy.libra.dom.biz.vo.aop;

import com.galaxy.libra.dom.biz.vo.http.RiskAmntRequstBody;
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

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.vo.http.filter
 * @date 2019-09-04
 * @time 10:35
 * @p_name spring
 */
@Component
@Aspect
public class RiskAmntRequestCheck {

    @Autowired
    RiskAmntHttpHeaderCheck riskAmntHttpHeaderCheck;

    @Pointcut(value = "execution(* com.galaxy.libra.interfaces.dispatcher.AppController.*(..)) && args(he)"
            , argNames = "he")
    public void check(HttpEntity<RiskAmntRequstBody> he) {
    }

    @Around(value = "check(he)", argNames = "he")
    public Object doCheck(ProceedingJoinPoint joinPoint, HttpEntity<RiskAmntRequstBody> he) {
        final HttpHeaders headers = he.getHeaders();
        if (headers.containsKey("token")) {
            final String token = headers.get("token").get(0);
            final Boolean checkToken = riskAmntHttpHeaderCheck.checkToken(token);
            if (checkToken) {
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    System.out.println(throwable.getMessage());
                    return returnException();
                }
            } else {
                return returnWrong();
            }
        } else {
            return returnWrong();
        }
    }

    public ResponseEntity<String> returnWrong() {
        return new ResponseEntity<String>("token is unvalid or missed", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<String> returnException() {
        return new ResponseEntity<String>("service throws exception", HttpStatus.SERVICE_UNAVAILABLE);
    }

}
