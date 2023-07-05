package com.wuqin.admin.service;

import com.com.base.po.TTeacherInfo;
import com.github.pagehelper.PageInfo;
import com.wuqin.admin.dto.TeacherInfoDto;

public interface IStaffService {
    PageInfo<TTeacherInfo> list(TeacherInfoDto dto, int page, int limit);

    void add(TeacherInfoDto dto);

    void update(TTeacherInfo info);

    void delete(int id);
}
