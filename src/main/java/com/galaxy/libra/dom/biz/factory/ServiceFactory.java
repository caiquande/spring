package com.galaxy.libra.dom.biz.factory;

import com.galaxy.libra.dom.biz.service.es.EsDML;
import com.galaxy.libra.dom.biz.service.es.EsDQL;
import com.galaxy.libra.dom.biz.service.oracle.OracleDQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.app.dom.biz.factory
 * @date 2019-09-03
 * @time 10:44
 * @p_name spring
 */
@Configuration
public class ServiceFactory {

    @Bean
    public EsDML getEsDML() {
        return new EsDML();
    }

    @Bean
    public EsDQL getEsDQL() {
        return new EsDQL();
    }

    @Bean
    public OracleDQL getOracleDQL() {
        return new OracleDQL();
    }

}
