package com.galaxy.libra.app.dom.biz.vo.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class KafkaMessageSeiria implements Serializer<KafkaMessage> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, KafkaMessage data) {

        return JSONObject.toJSONBytes(data);
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }
}
