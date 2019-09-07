package com.galaxy.libra.dom.biz.entity.client;

import com.galaxy.libra.infra.config.OracleClientConfig;
import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg
 * @date 2019-08-26
 * @time 10:49
 * @p_name bigdataplatform
 */

public class OracleClient implements AutoCloseable {
    private static Connection con;

    public OracleClient(OracleClientConfig config) {
        con = getConnect(config.getUser(),config.getPw(),config.getUrl());
    }

    public static Connection getConnect(String user, String pw, String url) {
        OracleDriver oracleDriver = new OracleDriver();
        Properties properties = new Properties();
        properties.put("user", user);
        properties.put("password", pw);
        try {
            DriverManager.deregisterDriver(oracleDriver);
            //jdbc:oracle:thin:@10.0.11.121:1521:orcl
            con = oracleDriver.connect(url, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public void close() throws Exception {
        if (con != null) {
            con.close();
        } else {
            throw new NullPointerException("the connection is null");
        }
    }
}
