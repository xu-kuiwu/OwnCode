package com.wuqin.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.com.base.dao.TTeacherInfoMapper;
import com.com.base.po.TTeacherInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuqin.admin.dto.TeacherInfoDto;
import com.wuqin.admin.service.IStaffService;
import com.wuqin.common.utils.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class StaffService implements IStaffService {
    @Autowired
    private TTeacherInfoMapper tTeacherInfoMapper;

    @Override
    public PageInfo<TTeacherInfo> list(TeacherInfoDto dto, int page, int limit) {
        PageHelper.startPage(page, limit, true);
        Example example = new Example(TTeacherInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(dto.getTeacherName())) {
            criteria.andLike("teacherName", "%" + dto.getTeacherName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getStatus())) {
            criteria.andEqualTo("status", dto.getStatus());
        }
        if (!StringUtils.isEmpty(dto.getPost())) {
            criteria.andEqualTo("post", dto.getPost());
        }
        example.orderBy("id").desc();
        List<TTeacherInfo> list = tTeacherInfoMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public void add(TeacherInfoDto dto) {
        //获取数量
        int count = tTeacherInfoMapper.selectTeacherCount();
        TTeacherInfo tTeacherInfo = new TTeacherInfo();
        BeanUtil.copyProperties(dto, tTeacherInfo);
        String type = "0".equals(dto.getPost()) ? "Q" : "1".equals(dto.getPost()) ? "Z" : "O";
        tTeacherInfo.setStaffNo(type.concat(StrUtil.getTeacherNo(count + 1)));
        tTeacherInfoMapper.insertSelective(tTeacherInfo);
    }

    @Override
    public void update(TTeacherInfo info) {
        tTeacherInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public void delete(int id) {
        tTeacherInfoMapper.deleteByPrimaryKey(id);
    }
}
