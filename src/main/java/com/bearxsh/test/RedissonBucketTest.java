package com.bearxsh.test;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author bearx
 */
public class RedissonListTest {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://39.96.210.14:7777").setKeepAlive(true);
        RedissonClient client = Redisson.create(config);
        RList<String> list = client.getList("list:test");
        list.add("zhangsan");
        list.add("lisi");
        list.add("wanger");

    }
}
