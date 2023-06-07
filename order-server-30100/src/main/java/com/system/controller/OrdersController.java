package com.system.controller;


import com.alibaba.nacos.client.naming.utils.ThreadLocalRandom;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.system.base.BaseController;
import com.system.entity.Orders;
import com.system.feign.FansServerClient;
import com.system.result.R;
import com.system.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
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

    final RestTemplate restTemplate;

    final
    DiscoveryClient discoveryClient;
    final
    FansServerClient fansServerClient;
    final
    OrdersService ordersService;

    public OrdersController(OrdersService ordersService, FansServerClient fansServerClient, DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.ordersService = ordersService;
        this.fansServerClient = fansServerClient;
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

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

    @GetMapping("/info/{aid}")
    public R info(@PathVariable("aid") Long aid){
        List<Orders> ordersList = ordersService.list(new QueryWrapper<Orders>().eq("aid", aid));
        return R.ok().data("ordersList",ordersList);
    }
    @PostMapping("/save")
    public R info(@RequestBody Orders orders){
        log.info("订单信息{}",orders);
        orders.setCreated(LocalDateTime.now());
        orders.setUpdated(LocalDateTime.now());
        orders.setStatu(1);
        ordersService.save(orders);
        return R.ok();
    }

    @PostMapping("/qrcode")
    public R qrcode(@RequestBody String obj) throws IOException, WriterException {
        log.info(obj);
        QRCodeWriter qrCodeWriter1 = new QRCodeWriter();
        BitMatrix bitMatrix1 = qrCodeWriter1.encode(obj, BarcodeFormat.QR_CODE,600, 600);
            ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
                MatrixToImageWriter.writeToStream(bitMatrix1, "PNG", outputStream1);
                Base64.Encoder encoder1 = Base64.getEncoder();
                String str = "data:image/jpeg;base64,";
                String advUrl = str+encoder1.encodeToString(outputStream1.toByteArray());
                return R.ok().data("QRCode",advUrl);
    }
}
