package com.galaxy.libra.dom.biz.vo.http.filter;

import org.springframework.stereotype.Component;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.vo.http.filter
 * @date 2019-09-04
 * @time 11:04
 * @p_name spring
 */
@Component
public class RiskAmntHttpHeaderCheck {

    public Boolean checkToken(String token) {
        if (token != null) {
            return true;
        } else {
            return false;
        }
    }

}
