package com.system.service.impl;

import com.system.entity.Film;
import com.system.mapper.FilmMapper;
import com.system.service.FilmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuhan
 * @since 2023-06-04
 */
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements FilmService {

}
