package com.wuqin.admin.service;

import com.com.base.po.TParentInfo;
import com.github.pagehelper.PageInfo;
import com.wuqin.admin.dto.ParentInfoDto;
import com.wuqin.admin.dto.QryParentInfo;

import java.util.List;

public interface IParentService {
    PageInfo<TParentInfo> list(QryParentInfo qryParentInfo, int page, int limit);

    void add(ParentInfoDto dto);

    void update(TParentInfo dto);

    void delete(int id);
}
