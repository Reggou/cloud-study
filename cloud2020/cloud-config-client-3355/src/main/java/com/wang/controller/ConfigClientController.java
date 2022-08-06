package com.wang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * cmd下输入就可实现手动动态刷新  curl -X POST "http://localhost:3355/actuator/refresh"
 *
 * @author wzzmm
 * @since 2022/8/3 22:41
 */

@RestController
@RefreshScope //动态刷新
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
