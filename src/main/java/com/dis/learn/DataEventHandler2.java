package com.dis.learn;

import com.lmax.disruptor.EventHandler;

public class DataEventHandler2 implements EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent dataEvent, long l, boolean b) throws Exception {
        System.out.println("Event2: "+dataEvent.toString());
        new DataEventConsumer(dataEvent);
    }
}
