package com.wang.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author wzzmm
 * @since 2022/7/30 22:19
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    /**
     * 过滤器
     *
     * @param exchange =request response
     * @param chain =过滤器的链
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("MyLogGatewayFilter:" + new Date());
        String uname =
                exchange.getRequest().getQueryParams().getFirst("Uname");//请求是否带着Uname这个key
        if (uname == null){//存在但是为null也是非法
            log.info("Username:null");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 过滤器优先级
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
