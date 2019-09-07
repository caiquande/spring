package com.galaxy.libra.dom.biz.factory;

import com.galaxy.libra.dom.biz.service.es.EsDml;
import com.galaxy.libra.dom.biz.service.es.EsDql;
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
    public EsDml getEsDML() {
        return new EsDml();
    }

    @Bean
    public EsDql getEsDQL() {
        return new EsDql();
    }

    @Bean
    public OracleDQL getOracleDQL() {
        return new OracleDQL();
    }

}
