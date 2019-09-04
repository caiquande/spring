package com.galaxy.libra.dom.biz.entity.client;

import com.galaxy.libra.dom.biz.entity.DataClient;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EsClient implements DataClient<RestHighLevelClient>, AutoCloseable {

    private String host;
    private int port;
    private RestHighLevelClient esClient;

    public EsClient(String host, int port, String http) {
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

}
