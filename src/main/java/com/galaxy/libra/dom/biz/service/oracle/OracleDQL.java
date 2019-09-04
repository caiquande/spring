package com.galaxy.libra.dom.biz.service.oracle;

import com.galaxy.libra.dom.biz.entity.client.OracleClient;
import com.galaxy.libra.dom.biz.service.DataDQL;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.service
 * @date 2019-09-03
 * @time 13:58
 * @p_name spring
 */
@Component
public class OracleDQL implements DataDQL {

    public List<Map<String, Object>> runSingleSql(OracleClient oracleClient, String sql) throws Exception {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        try (final OracleClient client = oracleClient) {
            final ResultSet resultSet = client.getCon().createStatement().executeQuery(sql);
            final ResultSetMetaData metaData = resultSet.getMetaData();
            final int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                final HashMap<String, Object> tmp = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    tmp.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                }
                res.add(tmp);
            }
        }
        return res;
    }

    public Long getLongRes(OracleClient oracleClient, String sql) throws Exception {
        Long res = 0L;
        try (final OracleClient client = oracleClient) {
            final ResultSet resultSet = client.getCon().createStatement().executeQuery(sql);
            if (resultSet.next()) {
                res = resultSet.getLong(1);
            }
        }
        return res;
    }

    public String getStringRes(OracleClient oracleClient, String sql) throws Exception {
        String res = null;
        try (final OracleClient client = oracleClient) {
            final ResultSet resultSet = client.getCon().createStatement().executeQuery(sql);
            if (resultSet.next()) {
                res = resultSet.getString(1);
            }
        }
        return res;
    }

}
