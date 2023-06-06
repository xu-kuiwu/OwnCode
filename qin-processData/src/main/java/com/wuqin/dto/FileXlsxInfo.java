package com.wuqin.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class FileXlsxInfo {
    @ExcelProperty({"测试文件处理","客户号"})
    private String custNo;

    @ExcelProperty({"测试文件处理","身份证号"})
    private String idNumber;

    @ExcelProperty({"测试文件处理","类型"})
    private String type;

    @ExcelProperty({"测试文件处理","金额"})
    private String amount;

    @ExcelProperty({"测试文件处理","备注"})
    private String remark;
}
