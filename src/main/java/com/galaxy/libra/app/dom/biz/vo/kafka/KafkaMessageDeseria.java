package com.galaxy.libra.app.dom.biz.vo.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class KafkaMessageDeseria implements Deserializer<KafkaMessage> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }

    @Override
    public KafkaMessage deserialize(String topic, byte[] data) {
        return JSONObject.parseObject(data,KafkaMessage.class);
    }
}
