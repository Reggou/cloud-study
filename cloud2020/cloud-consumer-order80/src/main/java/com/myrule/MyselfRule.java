package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * com.netflix.loadbalancer.RoundRobinRule 轮询
 * com.netflix.loadbalancer.RandomRule 随机
 * com.netflix.loadbalancer.RetryRule 先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务
 * WeightedResponseTimeRule 对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择
 * BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
 * AvailabilityFilteringRule 先过滤掉故障实例，再选择并发较小的实例
 * ZoneAvoidanceRule 默认规则,复合判断server所在区域的性能和server的可用性选择服务器
 *
 * 负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标  ，每次服务重启动后rest接口计数从1开始。
 *
 * List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
 *
 * 如：   List [0] instances = 127.0.0.1:8002
 * 　　　List [1] instances = 127.0.0.1:8001
 *
 * 8001+ 8002 组合成为集群，它们共计2台机器，集群总数为2， 按照轮询算法原理：
 *
 * 当总请求数为1时： 1 % 2 =1 对应下标位置为1 ，则获得服务地址为127.0.0.1:8001
 * 当总请求数位2时： 2 % 2 =0 对应下标位置为0 ，则获得服务地址为127.0.0.1:8002
 * 当总请求数位3时： 3 % 2 =1 对应下标位置为1 ，则获得服务地址为127.0.0.1:8001
 * 当总请求数位4时： 4 % 2 =0 对应下标位置为0 ，则获得服务地址为127.0.0.1:8002
 * 如此类推......
 *
 * @author wzm
 * @since 2022/7/10
 */
@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();//定义为随机
    }
}
