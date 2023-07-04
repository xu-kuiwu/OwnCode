package com.wuqin.admin.service.impl;

import com.com.base.dao.TTeacherInfoMapper;
import com.com.base.po.TTeacherInfo;
import com.wuqin.admin.dto.TeacherInfoDto;
import com.wuqin.admin.service.IStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StaffService implements IStaffService {
    @Autowired
    private TTeacherInfoMapper tTeacherInfoMapper;

    @Override
    public List<TTeacherInfo> list() {

        return null;
    }

    @Override
    public void add(TeacherInfoDto dto) {

    }

    @Override
    public void update(TeacherInfoDto dto) {

    }

    @Override
    public void delete(int id) {

    }
}
