package com.galaxy.libra.dom.biz.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.event
 * @date 2019-09-08
 * @time 15:14
 * @p_name spring
 */
@Data
@AllArgsConstructor
public class RiskInsuredAmntEvent {
    private String polNo;
    private String insuredNo;
    private String riskCode;
    private String contNo;
    private String submitTime;
    private double res;
}
