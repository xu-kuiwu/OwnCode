package com.wuqin.admin.service;

import com.com.base.po.TCourseInfo;
import com.wuqin.admin.dto.CourseInfoDto;

import java.util.List;

public interface IParamService {
    List<TCourseInfo> queryCourselist(CourseInfoDto dto);
}
