package com.galaxy.libra.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.infra.config
 * @date 2019-09-07
 * @time 19:57
 * @p_name bigdata-platform-etl
 */
@Configuration
public class OrclConfig {

    @Bean
    @ConfigurationProperties("orcl")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

}
