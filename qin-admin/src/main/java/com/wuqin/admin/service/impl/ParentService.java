package com.wuqin.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.com.base.dao.TParentInfoMapper;
import com.com.base.po.TParentInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuqin.admin.dto.ParentInfoDto;
import com.wuqin.admin.dto.QryParentDto;
import com.wuqin.admin.service.IParentService;
import com.wuqin.common.utils.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class ParentService implements IParentService {
    @Autowired
    private TParentInfoMapper tParentInfoMapper;

    @Override
    public PageInfo<TParentInfo> list(QryParentDto dto, int page, int limit) {
        PageHelper.startPage(page, limit, true);
        Example example = new Example(TParentInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(dto.getParentName())) {
            criteria.andLike("parentName", "%" + dto.getParentName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getParentNo())) {
            criteria.andEqualTo("parentNo", dto.getParentNo());
        }
        if (!StringUtils.isEmpty(dto.getPhone())) {
            criteria.andEqualTo("phone", dto.getPhone());
        }
        example.orderBy("id").desc();
        List<TParentInfo> list = tParentInfoMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public void add(ParentInfoDto dto) {
        //获取数量
        int count = tParentInfoMapper.selectParentCount();
        TParentInfo tParentInfo = new TParentInfo();
        tParentInfo.setParentNo(StrUtil.getParentNo(count + 1));
        BeanUtil.copyProperties(dto, tParentInfo);
        tParentInfoMapper.insertSelective(tParentInfo);
    }

    @Override
    public void update(TParentInfo info) {
        tParentInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public void delete(int id) {

    }
}
