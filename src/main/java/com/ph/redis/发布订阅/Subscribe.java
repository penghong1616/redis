package com.ph.redis.发布订阅;

import com.ph.redis.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Subscribe extends Thread{
    private static String channelName="book";
    private volatile int count;
    private  PubSub pubSub=new PubSub();
    public void subMessage(){
        Jedis jedis= JedisPoolUtil.getJedis();
        jedis.subscribe(pubSub,channelName);
    }

    @Override
    public void run() {
        while(true)
            subMessage();
    }

    public static void main(String[] args) {
        Subscribe subscribe=new Subscribe();
        Thread threada=new Thread(subscribe,"订阅者-a");
        Thread threadb=new Thread(subscribe,"订阅者-b");
        threada.start();
        threadb.start();
    }
    class  PubSub extends JedisPubSub {
        @Override
        public void onMessage(String channel, String message) {
            System.out.println(Thread.currentThread().getName()+"订阅--"+channel+"--收到消息："+message);
        }
    }
}
