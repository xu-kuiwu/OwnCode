package com.wuqin.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.wuqin.dto.FileInfoDto;
import com.wuqin.service.IMapOrListExampleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MapOrListExampleService implements IMapOrListExampleService {
    @Override
    public void qryExample() {
        FileInfoDto infoDto = new FileInfoDto();
        infoDto.setName("张三");
        infoDto.setName_1("张三_1");
        infoDto.setName_2("张三_2");
        infoDto.setName_3("张三_3");
        infoDto.setName_4("张三_4");
        FileInfoDto infoDto1 = new FileInfoDto();
        infoDto.setName("李四");
        infoDto.setName_1("李四_1");
        infoDto.setName_2("李四_2");
        infoDto.setName_3("李四_3");
        infoDto.setName_4("李四_4");
        List<FileInfoDto> infoList = Lists.newArrayList();
        infoList.add(infoDto);
        infoList.add(infoDto1);

        /**
         * 对当前集合进行排序
         * 对名字进行排序
         */
        List<FileInfoDto> infoListSort = infoList.stream().sorted(Comparator.comparing(FileInfoDto::getName)).collect(Collectors.toList());

        /**
         * 提起当前集合参数，只包含name的新集合
         */
        List<String> nameList=infoList.stream().map(item -> item.getName()).collect(Collectors.toList());

        /**
         * list内判断
         * list内name只包含张三的数据
         */
        List<FileInfoDto> infoListFilter = infoList.stream().filter(r -> "张三".equals(r.getName())).collect(Collectors.toList());

        /**
         * list内name只包含张三的数据并排序
         */
        List<FileInfoDto> infoListFilterSort = infoList.stream()
                .filter(r -> "张三".equals(r.getName()))
                .sorted(Comparator.comparing(FileInfoDto::getName))
                .collect(Collectors.toList());

        /**
         * list<> --> map<String,List<>>
         */
        Map<String,List<FileInfoDto>> mapColler= infoList.stream().collect(Collectors.groupingBy(FileInfoDto::getName));

        /**
         * list --> list
         */
        List<FileInfoDto> copyInfoList = Lists.newArrayList();
        copyInfoList = infoList.stream().collect(Collectors.toList());

    }

    /**
     * 读取map数据，对map数据进行处理
     * 例：
     * 参数格式 Map<String,List<FileInfoDto>>
     * @param map
     */
    public void readMapInfo(Map map){
        if(map.isEmpty()){
            return;
        }
        Iterator<Map.Entry<String,String>> iterator= map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> next = iterator.next();
            String mapKey = next.getKey();
            String mapValue = next.getValue();
            if(StringUtils.isNotEmpty(mapValue)){
                List<FileInfoDto> info = JSON.parseArray(mapValue,FileInfoDto.class);
                log.info("map内value包含的数据信息 {}",info);
            }
        }
    }

    /**
     * 读取list数据，对list数据进行处理
     * 例：
     * @param list
     */
    public void readListInfo(List<Map> list){
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        Iterator<Map> iterator= list.iterator();
        while (iterator.hasNext()){
            Map<String,Object> infpMap = iterator.next();
            String name = Objects.toString(infpMap.get("name"),"");
            if(StringUtils.isEmpty(name)){
                //如果名字为空 直接删除集合
                iterator.remove();
            }
        }
    }
}
