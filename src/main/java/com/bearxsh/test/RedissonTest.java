package com.bearxsh.test;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.time.LocalDateTime;

/**
 * @author bearx
 */
public class RedissonTest {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://39.96.210.14:7777").setKeepAlive(true);
        RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock("lock:test");
        System.out.println(LocalDateTime.now() + " 尝试加锁...");
        lock.lock();
        System.out.println(LocalDateTime.now() + " 加锁成功！");
        System.out.println("执行业务逻辑...");
        Thread.sleep(60000);
        lock.unlock();
        System.out.println(LocalDateTime.now() + " 解锁成功！");
    }
}
