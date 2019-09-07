package com.galaxy.libra.dom.biz.factory;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import com.galaxy.libra.dom.biz.entity.client.OracleClient;
import com.galaxy.libra.dom.biz.entity.consumer.KafkaConsume;
import com.galaxy.libra.dom.biz.entity.provider.KafkaProvide;
import com.galaxy.libra.infra.config.EsConfig;
import com.galaxy.libra.infra.config.KafkaConsumerConfig;
import com.galaxy.libra.infra.config.KafkaProviderConfig;
import com.galaxy.libra.infra.config.OracleClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan(basePackages = "com.galaxy.libra.infra.config")
public class EntityFactory {

    @Bean
    public EsClient getEsClient(EsConfig esConfig) {
        return new EsClient(esConfig);
    }

    @Bean
    public OracleClient getOrcl(OracleClientConfig config) {
        return new OracleClient(config);
    }

    @Bean
    public KafkaProvide getKP(KafkaProviderConfig kafkaProviderConfig) {
        return new KafkaProvide(kafkaProviderConfig);
    }

    @Bean
    public KafkaConsume getKC(KafkaConsumerConfig kafkaConsumerConfig) {
        return new KafkaConsume(kafkaConsumerConfig);
    }

}
