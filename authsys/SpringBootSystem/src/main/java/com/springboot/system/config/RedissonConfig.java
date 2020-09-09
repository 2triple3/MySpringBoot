package com.springboot.system.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 10  * @author fancm
 * 11  * @date 2019-07-26
 * 12
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.1.100:6379");
        //config.useClusterServers()
                //.setScanInterval(2000)
//                .addNodeAddress("redis://192.168.1.101:6379", "redis://192.168.1.102:6379")
                //.addNodeAddress("redis://192.168.1.100:6379");

        RedissonClient redisson = Redisson.create(config);

        return redisson;
    }

}
