package com.dis.learn;

public class DataEventConsumer {

    public DataEventConsumer(DataEvent event){
        System.out.println("event: "+event.getValue());
    }

}
