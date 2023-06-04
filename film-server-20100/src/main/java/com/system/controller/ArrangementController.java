package com.system.controller;


import com.system.result.R;
import com.system.service.ArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/arrangement")
public class ArrangementController extends BaseController {
    @Autowired
    private ArrangementService arrangementService;
    //返回类型列表
    @GetMapping("/typelist/{id}")
    public R typeList(@PathVariable Long id) {
        // return R.success(arrangementService.getTypeList(id));
        return R.ok().data("typeList",arrangementService.getTypeList(id));
    }
    //根据电影id返回排片信息列表
    @GetMapping("/list/{fid}")
    public R list(@PathVariable Long fid) {
        // return Result.success(arrangementService.getByFid(fid));
        return R.ok().data("arrangementList",arrangementService.getByFid(fid));
    }
}
