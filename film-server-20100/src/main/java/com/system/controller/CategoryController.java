package com.system.controller;


import com.system.result.R;
import com.system.service.CategoryService;
import com.system.service.FilmService;
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
@RequestMapping("/category")
public class CategoryController extends BaseController {
    private final CategoryService categoryService;
    private final FilmService filmService;

    public CategoryController(CategoryService categoryService, FilmService filmService) {
        this.categoryService = categoryService;
        this.filmService = filmService;
    }

    //根据电影fid获取电影类型
       @GetMapping("/getFilmCategoryById/{id}")
    public R getFilmCategoryById(@PathVariable Long id) {
        // 传入电影id，根据电影id查询电影分类id，再根据电影分类id查询电影分类名称
        Long categoryId = filmService.getById(id).getCategoryId();
        // return Result.success(categoryService.getById(categoryId));
        return R.ok().data("category",categoryService.getById(categoryId));
    }
}

