package com.galaxy.libra;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.app
 * @date 2019-09-02
 * @time 18:11
 * @p_name spring
 */

@SpringBootApplication
public class App {
    public static void main(String[] args) throws Exception{
        final ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
//        try(final EsClient esClient = run.getBean(EsClient.class)){
//            final EsDML esDML = run.getBean(EsDML.class);
//            final ExistIndexListener existIndexListener = new ExistIndexListener(ExistIndexListener.getCallabl());
//            esDML.indexExistAsync(esClient,"test_async",existIndexListener);
//            System.out.println(existIndexListener.get(2, TimeUnit.SECONDS).toString());
//        }
//        Thread.sleep(4000);
//        run.close();
    }
}
