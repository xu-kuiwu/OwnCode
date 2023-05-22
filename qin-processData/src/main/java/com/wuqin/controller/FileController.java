package com.wuqin.controller;

import com.google.common.collect.Maps;
import com.wuqin.common.constants.Constant;
import com.wuqin.common.utils.IdWorkerUtilHeper;
import com.wuqin.common.vo.ResultVo;
import com.wuqin.service.IFileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "文件管理")
@RequestMapping("/file")
public class FileController {
    @Autowired
    private IFileInfoService iFileInfoService;

    @PostMapping("/fileCheck")
    @ApiOperation(value = "文件管理-读取文件记录到数据库中", notes = "文件读取")
    public ResultVo<Object> fileCheck(@RequestParam("file") MultipartFile file) {
        ResultVo<Object> result = new ResultVo();
        if (file.isEmpty()) {
            result.error500("导入文件为空");
            return result;
        }
        //支持1G
        if (file.getSize() > Constant.MAX_FILE_SIZE * 10) {
            result.error500("上传文件过大，最大允许上传10M");
            return result;
        }

        String originalFilename = file.getOriginalFilename();
        try {
            originalFilename = new String(originalFilename.getBytes("UTF-8"), StandardCharsets.UTF_8);
            String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            if (!"xls".equals(fileType) && !"xlsx".equals(fileType)) {
                log.info("----- 文件格式错误");
                result.error500("格式错误");
                return result;
            }
            String importNo = IdWorkerUtilHeper.getNextId();
            Integer count = iFileInfoService.fileSave(importNo, file.getInputStream(), fileType);
            //插入表格
            iFileInfoService.createSmpsFile(importNo, originalFilename);
        } catch (Exception e) {
            log.info("文件名称报错，错误信息【{}】", e.getMessage());
            result.error500("文件出现异常");
            return result;
        }
        return ResultVo.success();
    }

    @PostMapping("/readFileEmail")
    @ApiOperation(value = "文件管理-读取文件并发送邮件", notes = "邮件发送")
    public ResultVo readFileEmail() {
        iFileInfoService.readFileEmail();
        return ResultVo.success();
    }

    @PostMapping("/queryFile")
    @ApiOperation(value = "文件管理-查询文件信息", notes = "查询文件")
    public ResultVo queryFile(@RequestBody List<String> query) {
        Map map = Maps.newHashMap();

        List<String> qryFileList = iFileInfoService.queryFile(map);
        return ResultVo.success(qryFileList);
    }

    @PostMapping("/sendEmail")
    @ApiOperation(value = "文件管理-发送邮件", notes = "邮件发送")
    public ResultVo sendEmail() {
        iFileInfoService.sendEmail();
        return ResultVo.success();
    }

    @PostMapping("/newFile")
    @ApiOperation(value = "文件管理-读取并重新生成新文件", notes = "获取文件")
    public ResultVo newFile() {
        iFileInfoService.newFile();
        return ResultVo.success();
    }
}