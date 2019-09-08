package com.galaxy.libra.infra.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.infra.config
 * @date 2019-09-02
 * @time 17:24
 * @p_name bigdataplatform
 */
@Data
@Component
@ConfigurationProperties(prefix = "kafka.c")
public class KafkaConsumerConfig {
    private String servers;
    private String kDes;
    private String vDes;
    private String group;

    public Properties getProps() {
        final Properties properties = new Properties();
        properties.put("bootstrap.servers", this.servers);
        properties.put("key.deserializer", this.kDes);
        properties.put("value.deserializer", this.vDes);
        properties.put("group.id", this.group);
        return properties;
    }

}
