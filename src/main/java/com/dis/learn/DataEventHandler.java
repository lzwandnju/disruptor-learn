package com.dis.learn;

import com.lmax.disruptor.EventHandler;

public class DataEventHandler implements EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent dataEvent, long l, boolean b) throws Exception {
        new DataEventConsumer(dataEvent);
    }
}
