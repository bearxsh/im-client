package com.bearxsh.test;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author bearx
 */
public class RedissonBloomFilterTest {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://39.96.210.14:7777").setKeepAlive(true);
        RedissonClient client = Redisson.create(config);
        RBloomFilter<String> bloomFilter = client.getBloomFilter("bloomFilter:test2");
        bloomFilter.tryInit(10L, 0.03);
        bloomFilter.add("1");
        bloomFilter.add("2");
        bloomFilter.add("3");
        System.out.println(bloomFilter.contains("2"));
        System.out.println(bloomFilter.contains("4"));

    }
}
