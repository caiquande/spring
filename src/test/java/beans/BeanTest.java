package beans;

import com.galaxy.libra.dom.biz.entity.provider.KafkaProvide;
import com.galaxy.libra.dom.biz.factory.EntityFactory;
import com.galaxy.libra.dom.biz.vo.kafka.KafkaMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author caesar
 * @title
 * @description
 * @package PACKAGE_NAME
 * @date 2019-09-03
 * @time 10:11
 * @p_name spring
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = EntityFactory.class)

public class BeanTest {

    @Autowired
    KafkaProvide kafkaProvide;

    @Test
    public void test() {

        try (final KafkaProducer<Integer, KafkaMessage> producer = kafkaProvide.getProducer()) {
            System.out.println(producer.toString());
        }

    }

}
