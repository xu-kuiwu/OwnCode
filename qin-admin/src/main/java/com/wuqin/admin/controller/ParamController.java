package com.wuqin.admin.controller;

import com.com.base.po.TCourseInfo;
import com.wuqin.admin.common.vo.ResultVo;
import com.wuqin.admin.dto.CourseInfoDto;
import com.wuqin.admin.service.IParamService;
import com.wuqin.common.utils.CommUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 参数系统相关设置
 */
@RestController
@Api(tags = "参数设置")
@RequestMapping("/param")
public class ParamController {
    @Autowired
    IParamService iParamService;

    @GetMapping(value = "/getNo")
    @ApiOperation(value = "参数管理-生成唯一标识", notes = "生成唯一标识")
    public ResultVo getNo(String type) {
        return ResultVo.success(CommUtil.gainThreeNoByNo(type));
    }


    @PostMapping("/queryCourselist")
    @ApiOperation(value = "参数管理-查询课程列表信息", notes = "分页查询家长列表信息")
    public ResultVo queryCourselist(CourseInfoDto req) {
        List<TCourseInfo> List = iParamService.queryCourselist(req);
        return ResultVo.success(List);
    }
}
