package com.galaxy.libra.dom.biz.vo.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.vo.http
 * @date 2019-09-03
 * @time 18:12
 * @p_name spring
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskAmntRequstBody {
   private String contno;
   private String insuredNo;
   private String riskCode;
   private String polNo;

}
