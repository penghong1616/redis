package com.ph.redis.消息队列;

import com.ph.redis.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;

public class MessageProduct extends  Thread {
    private static String mKey="mQueue";
    private  volatile int count=0;
    public void putMessage(String message){
        Jedis jedis= JedisPoolUtil.getJedis();
        long size=jedis.lpush(mKey,message);
        System.out.println(Thread.currentThread().getName()+"队列大小："+size);
        count++;
    }

    @Override
    public synchronized void run() {
        for (int i=0;i<5;i++)putMessage("message---"+count);
    }

    public static void main(String[] args) {
        MessageProduct messageProduct=new MessageProduct();
        for (int i=0;i<5;i++){
            Thread thread=new Thread(messageProduct,"T"+i);
            thread.start();
        }
    }
}
