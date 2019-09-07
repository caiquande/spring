package com.galaxy.libra.dom.biz.entity.client;

import com.galaxy.libra.dom.biz.entity.DataClient;
import com.galaxy.libra.infra.config.EsConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.agg
 * @date 2019-08-26
 * @time 15:39
 * @p_name bigdataplatform
 */
@Data
public class EsClient implements DataClient<RestHighLevelClient>, AutoCloseable {

    private String host;
    private int port;
    private RestHighLevelClient esClient;

    public EsClient(EsConfig esConfig) {
        this.port=esConfig.getPort();
        this.host=esConfig.getHost();
        this.esClient = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, "http")));
    }

    @Override
    public RestHighLevelClient getClient() {
        return esClient;
    }

    @Override
    public void close() throws Exception {
        this.esClient.close();
    }

}
