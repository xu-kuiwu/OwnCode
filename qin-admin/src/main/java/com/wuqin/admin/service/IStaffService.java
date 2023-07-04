package com.wuqin.admin.service;

import com.com.base.po.TTeacherInfo;
import com.wuqin.admin.dto.TeacherInfoDto;

import java.util.List;

public interface IStaffService {
    List<TTeacherInfo> list();

    void add(TeacherInfoDto dto);

    void update(TeacherInfoDto dto);

    void delete(int id);
}
