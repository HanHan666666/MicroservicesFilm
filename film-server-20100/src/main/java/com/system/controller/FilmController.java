package com.system.controller;


import com.system.entity.Film;
import com.system.result.R;
import com.system.service.FilmService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("")
@Slf4j
public class FilmController extends BaseController {

        @Autowired
        private FilmService filmService;

        @GetMapping("/info/{id}")
        public R info(@PathVariable("id") Long id){
            log.info("服务器提供者执行...");
            Film film = filmService.getById(id);
            return R.ok().data("film",film);
        }

        //根据id获取电影信息
        @GetMapping("/getFilmById/{id}")
        public R getFilmById(@PathVariable Long id){
            Film film = filmService.getById(id);
            return R.ok().data("film",film);
        }

}
