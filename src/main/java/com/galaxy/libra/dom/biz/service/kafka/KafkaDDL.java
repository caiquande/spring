package com.galaxy.libra.dom.biz.service.kafka;

import com.galaxy.libra.dom.biz.service.DataDDL;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.dom.biz.service.kafka
 * @date 2019-09-04
 * @time 13:41
 * @p_name spring
 */
@Component
public class KafkaDDL implements DataDDL,AutoCloseable {
    private AdminClient adminClient;

    public KafkaDDL(@Value("${kafka.servers}") String url) {
        adminClient = getAdim(url);
    }

    public static AdminClient getAdim(String url) {
        final Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, url);
        final AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }

    @Override
    public void close() throws Exception {
        adminClient.close();
    }

    public void createTopic(String url, String topicName) {
        adminClient.createTopics(new ArrayList<NewTopic>() {
            {
                add(new NewTopic(topicName, 1, (short) 1));
            }
        });
    }

    public void describTopics(String url, String topicName) throws Exception {
        final DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(new ArrayList<String>() {
            {
                add(topicName);
            }
        });
        System.out.println(describeTopicsResult.values().get(topicName).get().name());
    }

}
