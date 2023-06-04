package com.system.controller;

import com.system.base.BaseController;
import com.system.entity.Fans;

import com.system.result.R;
import com.system.result.ResultCode;
import com.system.service.FansService;
import com.system.utils.JwtUtils;
import com.system.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@CrossOrigin("*")
@RestController
@RequestMapping("/fans")
public class FansController extends BaseController {
    @Autowired
    FansService fansService;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    public String port;

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
}
