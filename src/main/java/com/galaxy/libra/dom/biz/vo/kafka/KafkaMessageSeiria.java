package com.galaxy.libra.dom.biz.vo.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Serializer;

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
