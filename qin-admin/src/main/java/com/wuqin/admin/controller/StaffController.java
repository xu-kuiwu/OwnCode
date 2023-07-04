package com.wuqin.admin.controller;

import com.wuqin.admin.common.vo.ResultVo;
import com.wuqin.admin.dto.TeacherInfoDto;
import com.wuqin.common.utils.CommUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工管理Controller
 */
@RestController
@Api(tags = "员工管理")
@RequestMapping("/staff")
public class StaffController {
    @GetMapping(value = "/getXSNo")
    @ApiOperation(value = "员工管理-生成唯一标识", notes = "生成唯一标识")
    public ResultVo getXSNo(String type) {
        String uniqueNo = CommUtil.createCommonNo("type");

        return ResultVo.success(uniqueNo);
    }

    @PostMapping("/teacherList")
    @ApiOperation(value = "员工管理-查询老师列表信息", notes = "查询老师列表信息")
    public ResultVo teacherList() {

        return ResultVo.success();
    }

    @PostMapping("/add")
    @ApiOperation(value = "员工管理-新增老师信息", notes = "新增老师信息")
    public ResultVo add(TeacherInfoDto dto) {

        return ResultVo.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "员工管理-修改老师信息", notes = "修改老师信息")
    public ResultVo update(TeacherInfoDto dto) {

        return ResultVo.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "员工管理-删除老师信息", notes = "删除老师信息")
    public ResultVo delete(int id) {

        return ResultVo.success();
    }
}
