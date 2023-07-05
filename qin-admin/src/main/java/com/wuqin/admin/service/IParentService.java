package com.wuqin.admin.service;

import com.com.base.po.TParentInfo;
import com.github.pagehelper.PageInfo;
import com.wuqin.admin.dto.ParentInfoDto;
import com.wuqin.admin.dto.QryParentDto;

public interface IParentService {
    PageInfo<TParentInfo> list(QryParentDto dto, int page, int limit);

    void add(ParentInfoDto dto);

    void update(TParentInfo info);

    void delete(int id);
}
