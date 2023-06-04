package com.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.entity.Arrangement;
import com.system.mapper.ArrangementMapper;
import com.system.service.ArrangementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuhan
 * @since 2023-06-04
 */
@Service
public class ArrangementServiceImpl extends ServiceImpl<ArrangementMapper, Arrangement> implements ArrangementService {
    final
    ArrangementMapper arrangementMapper;

    public ArrangementServiceImpl(ArrangementMapper arrangementMapper) {
        this.arrangementMapper = arrangementMapper;
    }

    @Override
    public List<String> getTypeList(Long id) {
       return arrangementMapper.TypeList(id);
    }

    @Override
    public List<Arrangement> getByFid(Long fid) {
        QueryWrapper<Arrangement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fid", fid);
        return arrangementMapper.selectList(queryWrapper);
    }
}
