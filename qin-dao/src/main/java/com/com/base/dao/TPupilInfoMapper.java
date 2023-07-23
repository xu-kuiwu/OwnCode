package com.com.base.dao;

import com.com.base.po.TPupilInfo;
import com.wuqin.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TPupilInfoMapper extends BaseMapper<TPupilInfo> {
    int selectPupilCount();
}