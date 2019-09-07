package com.galaxy.libra.infra.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
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
@Component
public class OracleClientConfig {
    private @Value("${oracle.user}") String user;
    private @Value("${oracle.pw}") String pw;
    private @Value("${oracle.url}") String url;
}
