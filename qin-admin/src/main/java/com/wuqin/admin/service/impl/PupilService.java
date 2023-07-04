package com.wuqin.admin.service.impl;

import com.com.base.dao.TPupilInfoMapper;
import com.com.base.po.TPupilInfo;
import com.wuqin.admin.dto.PupilInfoDto;
import com.wuqin.admin.service.IPupilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PupilService implements IPupilService {
    @Autowired
    private TPupilInfoMapper tPupilInfoMapper;

    @Override
    public List<TPupilInfo> list() {
        return null;
    }

    @Override
    public void add(PupilInfoDto dto) {

    }

    @Override
    public void update(PupilInfoDto dto) {

    }

    @Override
    public void delete(int id) {

    }
}
