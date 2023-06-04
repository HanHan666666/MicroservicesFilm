package com.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.entity.Poster;
import com.system.result.R;
import com.system.service.PosterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.system.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuhan
 * @since 2023-06-04
 */
@RestController
@RequestMapping("/poster")
public class PosterController extends BaseController {
    final
    PosterService posterService;

    public PosterController(PosterService posterService) {
        this.posterService = posterService;
    }

    // list
    @GetMapping("/list")
    public R list(@RequestParam(defaultValue = "1") Integer current,
                  @RequestParam(defaultValue = "10") Integer size) {
        //分页
        Page<Poster> page = posterService.page(new Page<>(current, size), null);
        return R.ok().data("page",page);
    }
}
