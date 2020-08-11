package com.gxs.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//！！！！！！！！！！！！！！！自定义的这个类不能建在主启动类所在的包或其子包下

@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        //用我们选择的随机算法
        return new MyRandomRule();
        //return new RandomRule();
    }
}
