package com.galaxy.libra.dom.biz.vo.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg
 * @date 2019-08-29
 * @time 16:21
 * @p_name bigdataplatform
 */


@Data
@AllArgsConstructor
public class KafkaMessage implements Serializable {
    private String event;
    private String table;
    private Map<String,Object> values;
}
