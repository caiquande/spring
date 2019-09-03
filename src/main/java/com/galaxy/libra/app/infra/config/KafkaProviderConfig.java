package com.galaxy.libra.app.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.infra.config
 * @date 2019-09-02
 * @time 17:18
 * @p_name bigdataplatform
 */
@Component
public class KafkaProviderConfig {
    private String servers;
    private String keySeri;
    private String valSeri;

    public KafkaProviderConfig(@Value("${bootstrap.servers}") String servers, @Value("${key.serializer}") String keySeri,
                               @Value("${value.serializer}") String valSeri) {
        this.servers = servers;
        this.keySeri = keySeri;
        this.valSeri = valSeri;
    }
}
