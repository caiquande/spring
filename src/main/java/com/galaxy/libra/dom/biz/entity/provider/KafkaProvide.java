package com.galaxy.libra.dom.biz.entity.provider;


import com.galaxy.libra.dom.biz.entity.Provider;
import com.galaxy.libra.dom.biz.vo.kafka.KafkaMessage;
import com.galaxy.libra.infra.config.KafkaProviderConfig;
import lombok.Data;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg
 * @date 2019-08-26
 * @time 11:21
 * @p_name bigdataplatform
 */

@Data
public class KafkaProvide implements Provider, AutoCloseable {
    private KafkaProducer<Integer, KafkaMessage> producer;

    public KafkaProvide(KafkaProviderConfig kafkaProviderConfig) {
        producer = new KafkaProducer<Integer, KafkaMessage>(kafkaProviderConfig.getProps());
    }

    @Override
    public void close() throws Exception {
        producer.close();
    }

    public void sendMessge(String topicName, KafkaMessage mesg) {
        producer.send(new ProducerRecord<Integer, KafkaMessage>(topicName, 0, mesg));
    }

}
