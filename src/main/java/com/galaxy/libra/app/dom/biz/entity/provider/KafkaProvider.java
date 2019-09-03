package com.galaxy.libra.app.dom.biz.entity.provider;



import com.galaxy.libra.app.dom.biz.entity.Provider;
import com.galaxy.libra.app.dom.biz.vo.kafka.KafkaMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProvider implements Provider, AutoCloseable {
    private KafkaProducer<Integer, KafkaMessage> producer;

    public KafkaProvider(Properties props) {
        producer = getProducer(props);
    }

    static public KafkaProducer<Integer, KafkaMessage> getProducer(Properties pros) {
        return new KafkaProducer<Integer, KafkaMessage>(pros);
    }

    @Override
    public void close() throws Exception {
        producer.close();
    }

    public void sendMessge(String topicName, KafkaMessage mesg) {
        producer.send(new ProducerRecord<Integer, KafkaMessage>(topicName, 0, mesg));
    }

}
