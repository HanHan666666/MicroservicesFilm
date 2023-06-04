package com.system.mapper;

import com.system.entity.Fans;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Rye
 * @since 2023-05-25
 */
@Repository
public interface FansMapper extends BaseMapper<Fans> {
    @Select("select * from sys_fans where username = #{username}")
    Fans getByUsername(String username);
}
