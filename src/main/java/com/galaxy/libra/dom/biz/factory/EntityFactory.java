package com.galaxy.libra.dom.biz.factory;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import com.galaxy.libra.dom.biz.entity.client.OracleClient;
import com.galaxy.libra.dom.biz.entity.consumer.KafkaConsume;
import com.galaxy.libra.dom.biz.entity.provider.KafkaProvide;
import com.galaxy.libra.dom.biz.service.es.EsDML;
import com.galaxy.libra.dom.biz.service.es.EsDQL;
import com.galaxy.libra.dom.biz.service.oracle.OracleDQL;
import com.galaxy.libra.infra.config.KafkaConsumerConfig;
import com.galaxy.libra.infra.config.KafkaProviderConfig;
import org.springframework.beans.factory.annotation.Value;
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
public class EntityFactory {

    @Bean
    public EsClient getEsClient(@Value("${es.host}") String host,
                                @Value("${es.port}") int port,
                                @Value("http") String http) {
        return new EsClient(host, port, http);
    }

    @Bean
    public OracleClient getOrcl(@Value("${oracle.user}") String user,
                                @Value("${oracle.pw}") String pw,
                                @Value("${oracle.url}") String url) {
        return new OracleClient(user, pw, url);
    }

    @Bean
    public KafkaProviderConfig getKPconfig(@Value("${kafka.bootstrap.servers}") String servers,
                                           @Value("${kafka.key.serializer}") String keySeri,
                                           @Value("${kafka.value.serializer}") String valSeri) {
        return new KafkaProviderConfig(servers, keySeri, valSeri);
    }

    @Bean
    public KafkaConsumerConfig getKCconfig(@Value("${kafka.bootstrap.servers}") String servers,
                                           @Value("${kafka.key.deserializer}") String kDes,
                                           @Value("${kafka.value.deserializer}") String vDes,
                                           @Value("${kafka.group.id}") String group) {
        return new KafkaConsumerConfig(servers, kDes, vDes, group);
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
