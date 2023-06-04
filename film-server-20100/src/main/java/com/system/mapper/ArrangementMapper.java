package com.system.mapper;

import com.system.entity.Arrangement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuhan
 * @since 2023-06-04
 */
@Repository
public interface ArrangementMapper extends BaseMapper<Arrangement> {
    /**
     * 获得排片类型的列表：2D, 3D, IMAX
     */
    List<String> TypeList(Long id);
}
