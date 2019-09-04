package com.galaxy.libra.infra.xutils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galax.libra.xutils
 * @date 2019-08-30
 * @time 13:34
 * @p_name oracleTest
 */
@Service
public class Md5Tool {
    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    public String encodeInput(String input) throws Exception {
        if (input != null) {
            return DigestUtils.md5Hex(input);
        }else {
            return null;
        }
    }


    public String decodeInput(String input) throws Exception{
        return null;
    }

}
