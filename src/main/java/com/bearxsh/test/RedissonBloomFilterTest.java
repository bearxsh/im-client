package com.bearxsh.test;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author bearx
 */
public class RedissonBucketTest {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://39.96.210.14:7777").setKeepAlive(true);
        RedissonClient client = Redisson.create(config);
        RBucket<String> bucket = client.getBucket("bucket:test");
        bucket.set("2021");

    }
}
