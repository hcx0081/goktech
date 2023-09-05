package com.goktech.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.converters.string.StringImageConverter;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * {@code @description:}
 */
@Data
public class StudentData {
    // 学号
    @ExcelProperty("学号")
    private Long no;
    
    // 身份证号
    @ExcelProperty("身份证号")
    private Long id;
    
    // 报道时间
    @ExcelProperty("报道时间")
    @DateTimeFormat("yyyy年MM月dd日")
    private LocalDateTime reportTime;
    
    // 照片
    /**
     * 如果是String类型，必须指定转换器String：默认转换成String
     */
    @ExcelProperty(value = "照片", converter = StringImageConverter.class)
    private String photo;
    
    // 状态
    @ExcelProperty("状态")
    private String status;
}