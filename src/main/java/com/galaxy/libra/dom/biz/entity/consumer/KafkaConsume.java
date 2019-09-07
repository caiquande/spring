package com.galaxy.libra.dom.biz.entity.consumer;


import com.galaxy.libra.dom.biz.entity.Consumer;
import com.galaxy.libra.dom.biz.entity.client.EsClient;
import com.galaxy.libra.dom.biz.vo.kafka.KafkaMessage;
import com.galaxy.libra.infra.config.KafkaConsumerConfig;
import lombok.Data;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg
 * @date 2019-08-26
 * @time 16:39
 * @p_name bigdataplatform
 */

@Data
public class KafkaConsume implements Consumer, AutoCloseable {
    private KafkaConsumer<Integer, KafkaMessage> consumer;

    public KafkaConsume(KafkaConsumerConfig kafkaConsumerConfig) {
        consumer = new KafkaConsumer<Integer, KafkaMessage>(kafkaConsumerConfig.getProps());
    }

    @Override
    public void close() throws Exception {
        consumer.close();
    }

    public void consumeMessages(EsClient esClient, String topicName, int offset) throws Exception {

        TopicPartition tp = new TopicPartition(topicName, 0);
        consumer.assign(Arrays.asList(tp));
        consumer.seek(tp, offset);
        String index = "test_es_oracle";
        //consumer.subscribe(Arrays.asList(topicName));
//        try (final EsHelper esHelper = new EsHelper(esClient)) {
//            while (true) {
//                final ConsumerRecords<Integer, KafkaMessage> records = consumer.poll(Duration.ofMillis(100000));
//                final int flag = records.count();
//                logger.info("records.count() = " + records.count());
//                for (ConsumerRecord<Integer, KafkaMessage> r : records) {
//                    final String event = r.value().getEvent();
//                    if (event.equals("update")) {
//                        esHelper.updateSingleDoc(index, r.value().getValues().get("polno").toString(), r.value().getValues());
//                        logger.info("update done");
//                        final double calRes = esHelper.r13Cal(index, "0005528397", "111801", "210110000042499");
//                        logger.info("calRes = " + calRes);
//                    } else if (event.equals("insert")) {
//                        //esHelper.insertSingleDoc(index, r.value().getValues().get("polno").toString(), r.value().getValues());
//                        logger.info("insert done");
//                        final double calRes = esHelper.r13Cal(index, "0005528397", "111801", "210110000042499");
//                        logger.info("calRes = " + calRes);
//                    }
//                }
//            }
//        }

    }


}
