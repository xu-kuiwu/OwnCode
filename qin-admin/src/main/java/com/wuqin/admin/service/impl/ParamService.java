package com.wuqin.admin.service.impl;

import com.com.base.dao.TCourseInfoMapper;
import com.com.base.po.TCourseInfo;
import com.wuqin.admin.dto.CourseInfoDto;
import com.wuqin.admin.service.IParamService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class ParamService implements IParamService {
    @Autowired
    private TCourseInfoMapper tCourseInfoMapper;

    @Override
    public List<TCourseInfo> queryCourselist(CourseInfoDto dto) {
        Example example = new Example(TCourseInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(dto.getCourseName())) {
            criteria.andLike("parentName", "%" + dto.getCourseName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getCourseNo())) {
            criteria.andEqualTo("parentNo", dto.getCourseNo());
        }
        example.orderBy("id").desc();
        return tCourseInfoMapper.selectByExample(example);
    }
}
