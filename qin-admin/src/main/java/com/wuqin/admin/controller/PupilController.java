package com.wuqin.admin.controller;

import com.wuqin.admin.common.vo.ResultVo;
import com.wuqin.admin.dto.PupilInfoDto;
import com.wuqin.common.utils.CommUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生Controller
 */
@RestController
@Api(tags = "学生管理")
@RequestMapping("/pupil")
public class PupilController {
    @GetMapping(value = "/getXSNo")
    @ApiOperation(value = "学生管理-生成唯一标识", notes = "生成唯一标识")
    public ResultVo getXSNo(String type) {
        String uniqueNo = CommUtil.createCommonNo(type);

        return ResultVo.success(uniqueNo);
    }

    @PostMapping("/list")
    @ApiOperation(value = "学生管理-查询学生列表信息", notes = "查询学生列表信息")
    public ResultVo list() {

        return ResultVo.success();
    }

    @PostMapping("/add")
    @ApiOperation(value = "学生管理-新增学生信息", notes = "新增学生信息")
    public ResultVo add(PupilInfoDto dto) {

        return ResultVo.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "学生管理-修改学生信息", notes = "修改学生信息")
    public ResultVo update(PupilInfoDto dto) {

        return ResultVo.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "学生管理-删除学生信息", notes = "删除学生信息")
    public ResultVo delete(int id) {

        return ResultVo.success();
    }
}
