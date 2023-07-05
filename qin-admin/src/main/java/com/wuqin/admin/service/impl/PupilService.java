package com.wuqin.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.com.base.dao.TPupilInfoMapper;
import com.com.base.po.TPupilInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuqin.admin.dto.PupilInfoDto;
import com.wuqin.admin.service.IPupilService;
import com.wuqin.common.utils.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class PupilService implements IPupilService {
    @Autowired
    private TPupilInfoMapper tPupilInfoMapper;

    @Override
    public PageInfo<TPupilInfo> list(PupilInfoDto dto, int page, int limit) {
        PageHelper.startPage(page, limit, true);
        Example example = new Example(TPupilInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(dto.getPupilName())) {
            criteria.andLike("pupilName", "%" + dto.getPupilName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getParentNo())) {
            criteria.andEqualTo("parentNo", dto.getParentNo());
        }
        if (!StringUtils.isEmpty(dto.getCourseInfo())) {
            criteria.andEqualTo("courseInfo", dto.getCourseInfo());
        }
        example.orderBy("id").desc();
        List<TPupilInfo> list = tPupilInfoMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public void add(PupilInfoDto dto) {
        //获取数量
        int count = 0;
//        int count = tPupilInfoMapper.selectParentCount();
        TPupilInfo tPupilInfo = new TPupilInfo();
        tPupilInfo.setParentNo(StrUtil.getParentNo(count + 1));
        BeanUtil.copyProperties(dto, tPupilInfo);
        tPupilInfoMapper.insertSelective(tPupilInfo);
    }

    @Override
    public void update(TPupilInfo info) {
        tPupilInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public void delete(int id) {

    }
}
