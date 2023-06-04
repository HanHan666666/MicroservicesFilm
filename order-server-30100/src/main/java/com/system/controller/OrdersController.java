package com.system.controller;


import com.alibaba.nacos.client.naming.utils.ThreadLocalRandom;
import com.system.base.BaseController;
import com.system.feign.FansServerClient;
import com.system.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Rye
 * @since 2023-05-29
 */
@RestController
@Slf4j
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    FansServerClient fansServerClient;

//    @GetMapping("/instances")
//    public R list(){
//        List<ServiceInstance> instances = discoveryClient.getInstances("fans-server");
//        int index = ThreadLocalRandom.current().nextInt(instances.size());
//        String url = instances.get(index).getUri()+"/fans/info/"+"13333333333";
//        return restTemplate.getForObject(url,R.class);
//    }
    @GetMapping("/fans1")
    public String fans1() {
        // 消费者 ，调用fans-server服务中 /fans/port 返回调用实例端口
        // return restTemplate.getForObject("http://fansserver/fans/port",String.class);
        return fansServerClient.port();
    }


    @GetMapping("/fansById")
    public R fansById() {
        // 需要调用 fans-server服务
        return fansServerClient.check(15035356688L);
    }

    @GetMapping("/fans")
    public String fans(){
        return restTemplate.getForObject("http://fans-server/fans/port",String.class);
    }

    @GetMapping("/test")
    public R list(){
        return restTemplate.getForObject("http://fans-server/fans/info/13333333333", R.class);
    }

    @GetMapping("/info")
    public R info(){
        Long id = 1L; //大话西游
        log.info("服务消费者执行");
        String url ="http://localhost:20100/film/info/"+id;
        R r = restTemplate.getForObject(url, R.class);
        log.info("服务执行结果--{}",r);
        return r;
    }
}
