package com.wuqin.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wuqin.common.exception.WQException;
import com.wuqin.dto.FileInfoDto;
import com.wuqin.service.IFileInfoService;
import com.wuqin.utils.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FileInfoService implements IFileInfoService {
    @Value("${spring.mail.from}")
    private String from;
    private static final int pageNum = 1000;
    @Autowired
    private EmailUtil emailUtil;

    @Override
    public Integer fileSave(String importNo, InputStream imputStream, String fileType) throws IOException {
        int saveCount = 0;
        //解析excel文件流
        Workbook wb = null;
        if ("xls".equals(fileType)) {
            wb = new HSSFWorkbook(imputStream);
        } else if ("xlsx".equals(fileType)) {
            wb = new XSSFWorkbook(imputStream);
        }
        if (wb == null) {
            throw new WQException("fileSave() error");
        }
        Sheet sheet = wb.getSheetAt(0);
        int firsRow = sheet.getFirstRowNum();//起始数
        log.info("============ 文件起始数：{}", firsRow);
        int lastRow = sheet.getLastRowNum();//最后一行数
        log.info("============ 文件最后一行数：{}", lastRow);
        int rowCount = lastRow - firsRow + 1;//总行数
        log.info("============ 文件总条数：{}", rowCount);
        if (rowCount > 50000) {
            throw new WQException("单次文件不能超过50000条，建议分文件");
        }
        if (rowCount <= 1) {
            return saveCount;
        }

        int currentIndex = firsRow + 1;//查询起始条数
        int istBatchCount = 0;//跑批数量
        List<String> istCustList = Lists.newArrayList();
        List<FileInfoDto> fileDtoList = Lists.newArrayList();
        for (int i = 0; i < rowCount / 1000 + 1; i++) {
            List<FileInfoDto> fileCustList = Lists.newArrayList();
            List<String> custList = Lists.newArrayList();
            int bat = 1001;
            if (i == (rowCount / 1000)) {
                bat = rowCount - i * 1000 + 1;
            }
            for (int j = 0; j < bat; j++) {
                Row row = sheet.getRow(currentIndex);
                currentIndex++;
                if (row == null || row.getCell(1) == null) {
                    continue;
                }
                Cell cell_0 = row.getCell(0);
                if (cell_0 != null) {
                    cell_0.setCellType(CellType.STRING);
                }
                String userNo = cell_0 == null ? "" : cell_0.getStringCellValue().trim();

                Cell cell_1 = row.getCell(1);
                if (cell_1 != null) {
                    cell_1.setCellType(CellType.STRING);
                }
                String phoneNo = cell_1 == null ? "" : cell_1.getStringCellValue().trim();

                Cell cell_2 = row.getCell(2);
                if (cell_2 != null) {
                    cell_2.setCellType(CellType.STRING);
                }
                String custId = cell_2 == null ? "" : cell_2.getStringCellValue().trim();

                Cell cell_3 = row.getCell(3);
                if (cell_3 != null) {
                    cell_3.setCellType(CellType.STRING);
                }
                String custName = cell_3 == null ? "" : cell_3.getStringCellValue().trim();

                custList.add(custId);
                FileInfoDto infoDto = new FileInfoDto();
                infoDto.setName(custName);
                fileCustList.add(infoDto);
            }
            log.info("第{}次循环处理数据：{}", i + 1, fileCustList.size());
            if (custList.size() <= 0) {
                continue;
            }
            Map custMap = Maps.newHashMap();
            custMap.put("importNo", importNo);
            custMap.put("custList", custList);
            //查询数据库，验证是否有重复数据
            // Mapper.select   查回数据类型  List<String> existCustList
            List<String> existCustList = Lists.newArrayList();//默认查询返回数据类型

            for (int z = 0; z < fileCustList.size(); z++) {
                FileInfoDto zDto = fileCustList.get(z);
                String custName = zDto.getName();
                if (existCustList.contains(custName)) {
                    continue;
                }
                istCustList.add(custName);//需要处理的用户
                fileDtoList.add(zDto);
                istBatchCount++;
            }
            log.info("第{}次循环去重后处理数据：{}", i + 1, fileDtoList.size());
            if (istBatchCount % 1000 == 0) {
                //数据落表
                //Mapper.insert
                fileDtoList.clear();
            }
        }
        if (fileDtoList.size() > 0) {
            //数据落表
            //Mapper.insert
            fileDtoList.clear();
        }
        log.info("============= 总处理数据量；{}", istCustList.size());
        return istCustList.size();
    }

    @Override
    public void createSmpsFile(String importNo, String originalFilename) {

    }

    @Override
    public void readFileEmail() {
        log.info("================ sendEmail ==================== ");
        //表格导出类
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        //设置sheet
        SXSSFSheet workbookSheet = workbook.createSheet("校验后异常明细");
        Row sheetRow = null;

        //创建表头
        sheetRow = workbookSheet.createRow(0);
        //创建标题单元格
        sheetRow.setHeight((short) (26.25 * 20));
        sheetRow.createCell(0).setCellValue("流水号");
        sheetRow.createCell(1).setCellValue("交易类型");
        sheetRow.createCell(2).setCellValue("客户号");
        sheetRow.createCell(3).setCellValue("手机号");
        sheetRow.createCell(4).setCellValue("证件号");
        sheetRow.createCell(5).setCellValue("产品类型");
        sheetRow.createCell(6).setCellValue("券编号");
        sheetRow.createCell(7).setCellValue("积分流水号");
        sheetRow.createCell(8).setCellValue("活动名称");
        sheetRow.createCell(9).setCellValue("产品名称");
        sheetRow.createCell(10).setCellValue("状态");
        sheetRow.createCell(11).setCellValue("时间");
        sheetRow.createCell(12).setCellValue("备注");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterDay = sdf.format(cal.getTime());
        Date date = null;
        try {
            date = sdf.parse(yesterDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int month = date.getMonth() + 1;
        if (month == 12) {
            month = 0;
        }
        Calendar calnow = Calendar.getInstance();
        String nowDate = sdf.format(calnow.getTime());
        Map<String, Object> map = Maps.newHashMap();
        map.put("month", String.valueOf(month));
        map.put("transDateBegin", yesterDay);
        map.put("transDateEnd", nowDate);

//        List<fileInfoDto> info = 查询数据库  以下案例
        List<FileInfoDto> infoList = Lists.newArrayList();
        log.info("========== 通过库里查询到的数据信息：{}", infoList);

        if (CollectionUtils.isEmpty(infoList)) {
            log.info("====== 未获取到信息 =======");
            return;
        }
        int count = infoList.size();
        //防止溢出 最多只处理100万条数据
        if (count > 1000000) {
            count = 1000000;
        }

        //每次处理1万条数据
        for (int i = 0; i < count / 10000; i++) {
            //查表操作，有数据可直接用
            map.put("currIndex", i * 10000);
            map.put("pageSize", 10000);
            //分页查询数据明细信息
            //查询数据库 以下案例 List<fileInfoDto> fileList = Mapper.select....
            List<FileInfoDto> fileList = Lists.newArrayList();
            //
            for (int j = 0; j < fileList.size(); j++) {
                FileInfoDto dto = fileList.get(j);
                sheetRow = workbookSheet.createRow(j + i * 10000 + 1);

                sheetRow.createCell(0).setCellValue(dto.getName());
                sheetRow.createCell(1).setCellValue(dto.getName_1());
                sheetRow.createCell(2).setCellValue(dto.getName_2());
                sheetRow.createCell(3).setCellValue(dto.getName_3());
                sheetRow.createCell(4).setCellValue(dto.getName_4());
                sheetRow.createCell(5).setCellValue(dto.getName_5());
                sheetRow.createCell(6).setCellValue(dto.getName_6());
                sheetRow.createCell(7).setCellValue(dto.getName_7());
                sheetRow.createCell(8).setCellValue(dto.getName_8());
                sheetRow.createCell(9).setCellValue(dto.getName_9());
                sheetRow.createCell(10).setCellValue(dto.getName_10());

                String format = "";
                if (!StringUtils.isEmpty(dto.getName_11())) {
                    DateTime parse = DateUtil.parse(dto.getName_11(), DatePattern.PURE_DATE_PATTERN);
                    format = DateUtil.format(parse, DatePattern.NORM_DATE_PATTERN);
                }
                sheetRow.createCell(11).setCellValue(format);
                sheetRow.createCell(12).setCellValue(dto.getName_12());
            }
        }
        workbookSheet.setDefaultRowHeight((short) (16.5 * 20));
        workbookSheet.trackAllColumnsForAutoSizing();

        for (int a = 0; a < 12; a++) {
            workbookSheet.autoSizeColumn(a);
            workbookSheet.setColumnWidth(a, 5066);
        }
        String fileName = "fileName_" + yesterDay + ".xlsx";
        log.info(" ========== fileName:{}", fileName);
        File file = new File(fileName);
        try {
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
            workbook.write(output);
            output.close();
        } catch (Exception e) {
            log.info("写入文件失败，异常信息：{}", e.getMessage());
        }

        //发送邮件
        sendFileEailCase(file, yesterDay);
    }

    @Override
    public List<String> queryFile(Map reqMap) {


        return Lists.newArrayList();
    }

    @Override
    public void sendEmail() {
        String to = "xukuiwu@aliyun.com";
        String tile = "测试邮件_20220622";
        String text = "本邮件测试专用";
//        emailUtil.sendMessage(from,to,tile,text);

        String tile_1 = "测试邮件带附件_20220622";
        String text_1 = "-------------------本邮件测试专用!此处有附件 ---------------------------- ";
        String to_1 = "xukuiwu@aliyun.com,1127524534@qq.com";
        String path = "C:\\Users\\MI\\Desktop\\2022年5月收入差异明细.xlsx";
        File file = new File(path);
        emailUtil.sendMessageCarryFile(from, to_1, tile_1, text_1, file);
    }

    private Boolean sendFileEailCase(File file, String yesterDay) {
        log.info("=========start sendFileEamil=========");
        Example example = new Example(FileInfoDto.class);
        example.createCriteria().andEqualTo("name", "张三");
//        List<fileInfoDto> list = Mapper.selectByExample(example);
        List<FileInfoDto> list = Lists.newArrayList();

        try {
            MultiPartEmail sEmail = new MultiPartEmail();
            sEmail.addTo("收件人邮箱@email.com");//收件人邮箱
            sEmail.setHostName("hostName");//url 地址
            sEmail.setAuthentication("userName", "password");//userName账号 password密码
            sEmail.setFrom("email", "scrm");//email 邮箱账号(fajianren@email.com) name 邮箱名称
            sEmail.setSubject("异常情况统计_" + yesterDay);//
            sEmail.setMsg("您好，异常情况报表，请查收！");
            sEmail.attach(file);
            sEmail.send();
            log.info("==================end 发送结束，邮件发送成功 ==============");
        } catch (Exception e) {
            log.info("邮件发送失败，失败信息：{}", e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public void newFile() {
        long start = System.currentTimeMillis();
        String fileName = "";
        try {
            writeFile(fileName);
        } catch (Exception e) {
            log.error("-------- 数据异常", e);
        }
        log.info("----------end 耗时:{} s", (System.currentTimeMillis() - start) / 1000);
    }

    /**
     * 文件读取重新生成新文件
     * @param fileName  文件名称 txt格式
     * @throws Exception
     */
    private void writeFile(String fileName) throws Exception {
        InputStreamReader isr = null;
        BufferedReader br = null;
        String lineTxt = null;
        //新文件地址
        File nFile = new File("C:\\Users\\MI\\Desktop\\sql.sql");
        BufferedWriter outPut = new BufferedWriter(new FileWriter(nFile));

        File file = null;
        int count = 0;
        try {
            file = new File(fileName);
            isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            br = new BufferedReader(isr);
            lineTxt = br.readLine();
            if (StringUtils.isEmpty(lineTxt)) {
                log.info("-------- 空文件 不处理");
                return;
            }
            while (lineTxt != null) {
                log.info("-----------count :{}, lineTxt:{}", count, lineTxt);
                String[] str = lineTxt.split("\\|");
                String data_1 = str[1];
                String data_2 = str[2];
                String data_3 = str[3];
                String data_4 = str[4];
                String data_5 = str[5];

                outPut.write(data_1);
                outPut.write("\r\n");
                outPut.write(data_2);
                outPut.write("\r\n");
                outPut.write(data_3);
                outPut.write("\r\n");
                outPut.write(data_4);
                outPut.write("\r\n");
                outPut.write(data_5);
                outPut.write("\r\n");
                outPut.write("\r\n");
                count++;
                lineTxt = br.readLine();
            }
            log.info("---------test ok");
            log.info("----共读取条数 count=[{}] 条记录", count);
        } catch (Exception e) {
            log.error("------ 数据异常", e);
        }finally {
            if(br!= null){
                br.close();
            }
            if(isr != null){
                isr.close();
            }
            outPut.flush();
            outPut.close();
        }
    }
}
