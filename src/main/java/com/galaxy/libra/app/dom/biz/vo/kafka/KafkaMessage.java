package com.galaxy.libra.app.dom.biz.vo.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
public class KafkaMessage implements Serializable {
    private String event;
    private String table;
    private Map<String,Object> values;
}
