package com.galaxy.libra.app.dom.biz.entity.client;

import com.galaxy.libra.app.dom.biz.entity.DataClient;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EsClient implements DataClient<RestHighLevelClient>, AutoCloseable {

    private String host;
    private int port;
    private RestHighLevelClient esClient;

    public EsClient(@Value("${es.host}") String host, @Value("${es.port}") int port, @Value("http") String http) {
        this.host = host;
        this.port = port;
        this.esClient = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, http)));
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public RestHighLevelClient getEsClient() {
        return esClient;
    }

    @Override
    public RestHighLevelClient getClient() {
        return esClient;
    }

    @Override
    public void close() throws Exception {
        this.esClient.close();
    }

    public int printTest() {
        System.out.println("test print ");
        return 0;
    }

}
