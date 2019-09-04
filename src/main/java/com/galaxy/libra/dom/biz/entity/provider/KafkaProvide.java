package com.galaxy.libra.dom.biz.entity.provider;



import com.galaxy.libra.dom.biz.entity.Provider;
import com.galaxy.libra.dom.biz.vo.kafka.KafkaMessage;
import com.galaxy.libra.infra.config.KafkaProviderConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProvide implements Provider, AutoCloseable {
    private KafkaProducer<Integer, KafkaMessage> producer;

    public KafkaProvide(KafkaProviderConfig kafkaProviderConfig) {
        producer = new KafkaProducer<Integer, KafkaMessage>(kafkaProviderConfig.getProps());
    }

    public KafkaProducer<Integer, KafkaMessage> getProducer() {
        return producer;
    }

    @Override
    public void close() throws Exception {
        producer.close();
    }

    public void sendMessge(String topicName, KafkaMessage mesg) {
        producer.send(new ProducerRecord<Integer, KafkaMessage>(topicName, 0, mesg));
    }

}
