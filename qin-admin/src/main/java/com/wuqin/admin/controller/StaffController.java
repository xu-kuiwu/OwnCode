package com.wuqin.admin.controller;

import com.com.base.po.TTeacherInfo;
import com.github.pagehelper.PageInfo;
import com.wuqin.admin.common.vo.PageQuery;
import com.wuqin.admin.common.vo.ResultVo;
import com.wuqin.admin.dto.TeacherInfoDto;
import com.wuqin.admin.service.IStaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工管理Controller
 */
@RestController
@Api(tags = "员工管理")
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private IStaffService iStaffService;

    @PostMapping("/teacherList")
    @ApiOperation(value = "员工管理-查询老师列表信息", notes = "查询老师列表信息")
    public ResultVo teacherList(@RequestBody PageQuery<TeacherInfoDto> req) {
        PageInfo<TTeacherInfo> List = iStaffService.list(req.getData(), req.getPage(), req.getLimit());
        return ResultVo.success(List);
    }

    @PostMapping("/add")
    @ApiOperation(value = "员工管理-新增老师信息", notes = "新增老师信息")
    public ResultVo add(TeacherInfoDto dto) {
        iStaffService.add(dto);
        return ResultVo.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "员工管理-修改老师信息", notes = "修改老师信息")
    public ResultVo update(TTeacherInfo info) {
        iStaffService.update(info);
        return ResultVo.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "员工管理-删除老师信息", notes = "删除老师信息")
    public ResultVo delete(int id) {

        return ResultVo.success();
    }
}
