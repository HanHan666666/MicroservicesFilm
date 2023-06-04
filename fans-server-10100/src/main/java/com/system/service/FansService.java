package com.system.service;

import com.system.entity.Fans;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Rye
 * @since 2023-05-25
 */
public interface FansService extends IService<Fans> {
    Fans getByUsername(String username);
}
