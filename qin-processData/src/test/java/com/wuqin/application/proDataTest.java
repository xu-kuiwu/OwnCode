package com.wuqin.application;

import com.google.common.collect.Lists;
import com.wuqin.controller.FileController;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Slf4j
public class proDataTest {

    @Autowired
    FileController FileController;

    @Test
    public void file(){
        String path = "C:\\Users\\MI\\Desktop\\2022年5月收入差异明细.xlsx";
        File file= new File(path);
        try {
            FileInputStream fileStream = new FileInputStream(file);
            MultipartFile mFile = new MockMultipartFile(file.getName(),file.getName(),ContentType.APPLICATION_OCTET_STREAM.toString(),fileStream);
            FileController.fileCheck(mFile);
        }catch (Exception e){
            log.error("文件处理异常，错误信息:{}",e.getMessage());
        }
    }

    @Test
    public void queryFile(){
        try {
            List<String> list = Lists.newArrayList();
            FileController.queryFile(list);
        }catch (Exception e){
            log.error("文件处理异常，错误信息:{}",e.getMessage());
        }
    }

    @Test
    public void sendEmail(){
        try {
            FileController.sendEmail();
        }catch (Exception e){
            log.error("文件处理异常，错误信息:{}",e.getMessage());
        }
    }
}
