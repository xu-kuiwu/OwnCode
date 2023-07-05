package com.wuqin.controller;

import com.wuqin.common.utils.CommUtil;
import com.wuqin.common.vo.ResultVo;
import com.wuqin.service.IMapOrListExampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "方法管理")
@RequestMapping("/example")
public class MapOrListExampleController {
    @Autowired
    IMapOrListExampleService mapOrListExampleService;


    @GetMapping(value = "/getPordNo")
    @ApiOperation(value = "产品管理-生成唯一标识", notes = "生成唯一标识")
    public ResultVo getPordNo(String type) {
        String uniqueNo = CommUtil.gainThreeNoByNo("type");
        return ResultVo.success(uniqueNo);
    }

    @PostMapping("/list")
    @ApiOperation(value = "方法管理-常用方法", notes = "list相关方法")
    public ResultVo get() {
        mapOrListExampleService.qryExample();
        return ResultVo.success();
    }
}
