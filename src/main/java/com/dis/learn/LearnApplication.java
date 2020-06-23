package com.dis.learn;

import com.lmax.disruptor.EventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LearnApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(LearnApplication.class, args);
        List<EventHandler<DataEvent>> eventHandlerList = new ArrayList<>();
        eventHandlerList.add(new DataEventHandler());
        eventHandlerList.add(new DataEventHandler2());
        DisruptorManager.init(eventHandlerList);
        for (int i = 0; true; i++) {
            DisruptorManager.putDataToQueue(i);
            Thread.sleep(1000);
        }
    }

}
