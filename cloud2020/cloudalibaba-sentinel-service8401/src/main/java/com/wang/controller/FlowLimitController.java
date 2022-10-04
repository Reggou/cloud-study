package com.wang.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author wzzmm
 * @since 2022/9/26 22:30
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }

    //慢调用比例-熔断条件：
    //在1000毫秒，也就是1秒内，
    // 如果发送到/testD 的请求数数量大于5，
    // 并且在这些请求中，所有请求的响应时长
    // （因为比例与阈值为1，所以是所有的请求响应时长）都大于500毫秒，也就是都大于0.5秒的时候，进入熔断状态。
    @GetMapping("/testD")
    public String testD() {
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);//睡1s超时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int age = 10/0;
        log.info("testD 测试RT");
        return "------testD";
    }
    @RequestMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2){
        return "testHotKeySuccess";
    }

    public String deal_testHotKey(@RequestParam(value = "p1",required = false)String p1,
                                  @RequestParam(value = "p2",required = false)String p2,
                                  BlockException blockException){
        return "deal_testHotKeyFail";
    }
}


