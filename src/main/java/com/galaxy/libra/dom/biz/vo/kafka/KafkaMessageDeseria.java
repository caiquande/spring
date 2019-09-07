package com.galaxy.libra.dom.biz.vo.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;


/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg
 * @date 2019-08-26
 * @time 15:39
 * @p_name bigdataplatform
 */

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
