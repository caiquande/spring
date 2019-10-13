package com.galaxy.libra;

import com.galaxy.libra.dom.biz.entity.client.EsClient;
import com.galaxy.libra.dom.biz.repo.ResumeRepo;
import com.galaxy.libra.interfaces.dto.GetFour;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;

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
        final ResumeRepo bean1 = run.getBean(ResumeRepo.class);
        final ArrayList<GetFour> getFours = bean1.findBySpecialRownum(5);
        getFours.forEach(e -> {
            System.out.println(e.getPolno() + " " + e.getContno());
        });
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
