package com.wuqin.admin.controller;

import com.com.base.po.TParentInfo;
import com.github.pagehelper.PageInfo;
import com.wuqin.admin.common.vo.PageQuery;
import com.wuqin.admin.common.vo.ResultVo;
import com.wuqin.admin.dto.ParentInfoDto;
import com.wuqin.admin.dto.QryParentDto;
import com.wuqin.admin.service.IParentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 家长管理Controller
 */
@RestController
@Api(tags = "家长管理")
@RequestMapping("/parent")
public class ParentController {
    @Autowired
    IParentService iParentService;

    @PostMapping("/list")
    @ApiOperation(value = "家长管理-查询家长列表信息", notes = "分页查询家长列表信息")
    public ResultVo list(@RequestBody PageQuery<QryParentDto> req) {
        PageInfo<TParentInfo> List = iParentService.list(req.getData(), req.getPage(), req.getLimit());
        return ResultVo.success(List);
    }

    @PostMapping("/add")
    @ApiOperation(value = "家长管理-新增家长信息", notes = "新增家长信息")
    public ResultVo add(ParentInfoDto dto) {
        iParentService.add(dto);
        return ResultVo.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "家长管理-修改家长信息", notes = "修改家长信息")
    public ResultVo update(TParentInfo dto) {
        iParentService.update(dto);
        return ResultVo.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "家长管理-删除家长信息", notes = "删除家长信息")
    public ResultVo delete(int id) {

        return ResultVo.success();
    }
}
