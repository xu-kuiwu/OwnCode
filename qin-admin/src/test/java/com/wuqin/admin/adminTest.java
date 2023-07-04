package com.wuqin.admin;

import com.wuqin.admin.controller.ParentController;
import com.wuqin.admin.dto.ParentInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Slf4j
public class adminTest {
    private ParentController parentController;

    @Test
    public void add(){
        ParentInfoDto dto = new ParentInfoDto();
        parentController.add(dto);
    }
}
