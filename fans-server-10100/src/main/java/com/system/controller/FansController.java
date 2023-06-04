package com.system.controller;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.system.base.BaseController;
import com.system.entity.Fans;

import com.system.result.R;
import com.system.result.ResultCode;
import com.system.service.FansService;
import com.system.utils.JwtUtils;
import com.system.utils.MD5Utils;
import com.system.utils.RedisUtil;
import com.system.utils.SendSMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@CrossOrigin("*")
@RestController
@RequestMapping("/")
@Slf4j
public class FansController extends BaseController {
    final
    FansService fansService;

    final
    JwtUtils jwtUtils;
    final
    RestTemplate restTemplate;

    @Value("${server.port}")
    public String port;

    public FansController(FansService fansService, JwtUtils jwtUtils, RestTemplate restTemplate, RedisUtil redisUtil) {
        this.fansService = fansService;
        this.jwtUtils = jwtUtils;
        this.restTemplate = restTemplate;
        this.redisUtil = redisUtil;
    }

    @GetMapping("/port")
    public String port() {
        return this.port;
    }

    @GetMapping("/getFilm")
    public R getFilm() {
        return restTemplate.getForObject("http://film-server/film/info/1", R.class);
    }

    @GetMapping("/check/{id}")
    public R check(@PathVariable Long id) {
        Fans fans = fansService.getById(id);
        if (fans != null) {
            return R.error(ResultCode.USER_HAS_EXIST, "该手机已经存在");
        }
        return R.ok();
    }


    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        Fans fans = fansService.getById(id);
        if (fans != null) {
            return R.ok().data("fans", fans);
        } else {
            return R.error(ResultCode.USER_NOT_EXIST, "用户不存在");
        }
    }

    @PostMapping("/login")
    public R login(@RequestBody Fans fans) {
        Fans f = fansService.getById(fans.getId());
        if (f != null) {
            if (f.getStatu() == 0) {
                return R.error(ResultCode.USER_ACCOUNT_FORBIDDEN, "账户被禁用");
            }
            if (f.getPassword().equals(MD5Utils.md5(
                    MD5Utils.inputPassToNewPass(fans.getPassword())))) {
                f.setToken(jwtUtils.generateToken(f.getId() + " "));
                return R.ok().data("fans", f);
            } else {
                return R.error(ResultCode.USER_ACCOUNT_ERROR, "密码错误");
            }
        } else {
            return R.error(ResultCode.USER_NOT_EXIST, "该用户不存在");
        }
    }

    // fans注册，传入手机号，手机验证码，密码，性别
    // 从配置文件中获取阿里云的accessKeyId和accessKeySecret
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.secretAccessKey}")
    private String accessKeySecret;

    @PostMapping("/register")
    public R register(@RequestBody Fans fans) throws Exception {
        Fans f = fansService.getById(fans.getId());

        if (f != null) {
            return R.error(ResultCode.USER_HAS_EXIST, "该手机已经存在");
        }
        // 从redis中获取验证码
        String code = (String) redisUtil.hget("code_sms", fans.getId().toString());
        if (code == null) {
            return R.error(ResultCode.OTHER_ERROR, "验证码已过期或不存在");
        }
        if (!code.equals(fans.getSms())) {
            return R.error(ResultCode.OTHER_ERROR, "验证码错误");
        }
        fans.setPassword(MD5Utils.md5(MD5Utils.inputPassToNewPass(fans.getPassword())));
        fansService.save(fans);
        return R.ok();
    }
    private final RedisUtil redisUtil;
    // 获取验证码接口
    @GetMapping("/getCode/{phone}")
    public R getCode(@PathVariable String phone) throws Exception {
        // 产生一个随机的四位数的code
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
        // 把生成的验证码和手机号存到redis
        redisUtil.hset("code_sms",phone, code, 60L);
        // SendSmsResponse smsResponse = SendSMS.Send(phone, accessKeyId, accessKeySecret, code);
        // log.info("短信接口返回的数据----------------{}", smsResponse);
        log.info("验证码为：{}", code);
        return R.ok();
    }
}
