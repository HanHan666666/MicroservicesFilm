package com.system.service.impl;

import com.system.entity.Category;
import com.system.mapper.CategoryMapper;
import com.system.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
