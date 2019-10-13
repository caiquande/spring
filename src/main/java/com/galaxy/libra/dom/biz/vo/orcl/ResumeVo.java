package com.galaxy.libra.dom.biz.vo.orcl;

import lombok.Data;

import javax.persistence.*;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.vo
 * @date 2019-09-07
 * @time 20:11
 * @p_name bigdata-platform-etl
 */
@Data
@Entity
@Table(name = "lcpol")
public class ResumeVo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String polno;
    private String contno;
    private String insuredno;
    private String riskcode;

}
