package com.ph.redis.消息队列;

import com.ph.redis.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class MessageConsumer extends  Thread{
    private String mName="mQueue";
    private volatile int count;
    public void consumerMessage(){
        Jedis jedis= JedisPoolUtil.getJedis();
        List<String> message=jedis.brpop(5,mName);
            System.out.println(Thread.currentThread().getName()+"--consumer message--"+message.get(1)+",count--"+count);
            count++;


    }

    @Override
    public void run() {
        while(true){
            consumerMessage();
        }
    }

    public static void main(String[] args) {
        MessageConsumer messageConsumer=new MessageConsumer();
        Thread t1=new Thread(messageConsumer,"thread-ca");
        Thread t2=new Thread(messageConsumer,"thread-cb");
        t1.start();
        t2.start();
    }
}
