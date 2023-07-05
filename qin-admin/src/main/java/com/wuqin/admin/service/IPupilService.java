package com.wuqin.admin.service;

import com.com.base.po.TPupilInfo;
import com.github.pagehelper.PageInfo;
import com.wuqin.admin.dto.PupilInfoDto;

public interface IPupilService {
    PageInfo<TPupilInfo> list(PupilInfoDto dto, int page, int limit);

    void add(PupilInfoDto dto);

    void update(TPupilInfo info);

    void delete(int id);
}
