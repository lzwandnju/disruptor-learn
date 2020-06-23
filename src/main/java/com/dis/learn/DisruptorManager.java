package com.dis.learn;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Executable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class DisruptorManager {

    private static ExecutorService threadPool;
    private static Disruptor<DataEvent> disruptor;
    private static RingBuffer<DataEvent> ringBuffer;

    private static AtomicLong daraNum = new AtomicLong();

    /**
     * 初始化disruptotor并启动程序
     * @param eventHandlerList
     */
    public static void init(List<EventHandler<DataEvent>> eventHandlerList){
        threadPool = Executors.newCachedThreadPool();
//      disruptor = new Disruptor<>(new DataEventFactory(),8*1024,threadPool, ProducerType.MULTI,new BlockingWaitStrategy());
        disruptor = new Disruptor<DataEvent>(new DataEventFactory(),8*1024,threadPool);
        ringBuffer = disruptor.getRingBuffer();
        for (EventHandler<DataEvent> eventHandler : eventHandlerList) {
            disruptor.handleEventsWith(eventHandler);
        }
        disruptor.start();
    }

    /**
     *  向ringBuuffer中存入传输的数据
     * @param message
     */
    public static void putDataToQueue(long message){
        if(daraNum.get()  == Long.MAX_VALUE){
            daraNum.set(0L);
        }
        long next = ringBuffer.next();
        try {
            ringBuffer.get(next).setValue(message);
            daraNum.incrementAndGet();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("向ringBuffer存入数据[{}]出现异常=>{}",message,e.getStackTrace());
        } finally {
            ringBuffer.publish(next);
        }
    }

    /**
     * 关闭线程池
     */
    public static void close(){
        threadPool.shutdown();
        disruptor.shutdown();
    }

}
