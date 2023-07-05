package com.wuqin.admin.controller;

import com.wuqin.admin.common.vo.ResultVo;
import com.wuqin.common.utils.CommUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数系统相关设置
 */
@RestController
@Api(tags = "参数设置")
@RequestMapping("/param")
public class ParamController {
    @GetMapping(value = "/getNo")
    @ApiOperation(value = "参数-生成唯一标识", notes = "生成唯一标识")
    public ResultVo getNo(String type) {
        return ResultVo.success(CommUtil.gainThreeNoByNo(type));
    }
}
