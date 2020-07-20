package com.ph.redis.发布订阅;

import com.ph.redis.util.JedisPoolUtil;
import com.ph.redis.消息队列.MessageConsumer;
import redis.clients.jedis.Jedis;

public class Publish extends Thread {
    private static String channelName="book";
    private volatile int count;
    public void publish(String message){
        Jedis jedis= JedisPoolUtil.getJedis();
        Long publisher=jedis.publish(channelName,message);
        System.out.println(Thread.currentThread().getName()+"--当前订阅数："+publisher+"--count:"+count);
        count++;
    }

    @Override
    public synchronized void run() {
        for (int i=0;i<5;i++){
            publish("message---"+count);
        }
    }

    public static void main(String[] args) {
        Publish publish=new Publish();
        for (int i=0;i<5;i++){
            Thread thread=new Thread(publish,"T-"+i);
            thread.start();
        }
    }
}
