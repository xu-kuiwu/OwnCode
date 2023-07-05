package com.wuqin.admin.controller;

import com.com.base.po.TPupilInfo;
import com.github.pagehelper.PageInfo;
import com.wuqin.admin.common.vo.PageQuery;
import com.wuqin.admin.common.vo.ResultVo;
import com.wuqin.admin.dto.PupilInfoDto;
import com.wuqin.admin.service.IPupilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生Controller
 */
@RestController
@Api(tags = "学生管理")
@RequestMapping("/pupil")
public class PupilController {
    @Autowired
    private IPupilService iPupilService;

    @PostMapping("/list")
    @ApiOperation(value = "学生管理-查询学生列表信息", notes = "分页查询学生列表信息")
    public ResultVo list(@RequestBody PageQuery<PupilInfoDto> req) {
        PageInfo<TPupilInfo> List = iPupilService.list(req.getData(), req.getPage(), req.getLimit());
        return ResultVo.success(List);
    }

    @PostMapping("/add")
    @ApiOperation(value = "学生管理-新增学生信息", notes = "新增学生信息")
    public ResultVo add(PupilInfoDto dto) {
        iPupilService.add(dto);
        return ResultVo.success(dto);
    }

    @PostMapping("/update")
    @ApiOperation(value = "学生管理-修改学生信息", notes = "修改学生信息")
    public ResultVo update(TPupilInfo dto) {
        iPupilService.update(dto);
        return ResultVo.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "学生管理-删除学生信息", notes = "删除学生信息")
    public ResultVo delete(int id) {

        return ResultVo.success();
    }
}
