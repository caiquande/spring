package com.galaxy.libra.infra.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.infra.config
 * @date 2019-09-02
 * @time 17:18
 * @p_name bigdataplatform
 */

@Data
@Component
public class KafkaProviderConfig {
    private @Value("${kafka.servers}") String servers;
    private @Value("${kafka.kSeri}") String kSeri;
    private @Value("${kafka.vSeri}") String vSeri;

    public Properties getProps() {
        final Properties properties = new Properties();
        properties.put("bootstrap.servers", this.servers);
        properties.put("key.serializer", this.kSeri);
        properties.put("value.serializer", this.vSeri);
        return properties;
    }

}
