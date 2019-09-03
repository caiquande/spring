package com.galaxy.libra.app.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.infra.config
 * @date 2019-09-02
 * @time 17:24
 * @p_name bigdataplatform
 */
@Component
public class KafkaConsumerConfig {
    private String servers;
    private String kDes;
    private String vDes;
    private String group;

    public KafkaConsumerConfig(@Value("${bootstrap.servers}") String servers, @Value("${key.deserializer}") String kDes,
                               @Value("${value.deserializer}") String vDes,
                               @Value("${group.id}") String group) {
        this.servers = servers;
        this.kDes = kDes;
        this.vDes = vDes;
        this.group = group;
    }
}
