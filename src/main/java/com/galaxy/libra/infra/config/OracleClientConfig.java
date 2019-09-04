package com.galaxy.libra.infra.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.infra.config
 * @date 2019-09-04
 * @time 13:24
 * @p_name spring
 */
@Data
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "oracle")
public class OracleClientConfig {
    private String user;
    private String pw;
    private String url;


}
