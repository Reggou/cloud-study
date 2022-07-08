package com.wang.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wzm
 * @since 2022/6/13
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced//restTemplate自带的负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
