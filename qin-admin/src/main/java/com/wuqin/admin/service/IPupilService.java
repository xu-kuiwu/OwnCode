package com.wuqin.admin.service;

import com.com.base.po.TParentInfo;
import com.com.base.po.TPupilInfo;
import com.wuqin.admin.dto.ParentInfoDto;
import com.wuqin.admin.dto.PupilInfoDto;

import java.util.List;

public interface IPupilService {
    List<TPupilInfo> list();

    void add(PupilInfoDto dto);

    void update(PupilInfoDto dto);

    void delete(int id);
}
