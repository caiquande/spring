package com.galaxy.libra.app.dom.biz.vo.es.model;


import com.galaxy.libra.app.dom.biz.vo.es.SearchContainer;

import java.util.ArrayList;

/**
 * @author caesar
 * @title
 * @description
 * @package com.sino.dev.interlayer.model.es
 * @date 2019-08-29
 * @time 11:47
 * @p_name oracleTest
 */
public class TwoFields0 implements SearchContainer {
    private String f0="amntpayyears";
    private String f1="payintv";

    public TwoFields0(String f0, String f1) {
        this.f0 = f0;
        this.f1 = f1;
    }

    @Override
    public int getFieldsCnt() {
        return 2;
    }

    @Override
    public ArrayList<String> getFieldsName() {
        return new ArrayList<String>(){
            {
                add(f0);
                add(f1);
            }
        };
    }

}
