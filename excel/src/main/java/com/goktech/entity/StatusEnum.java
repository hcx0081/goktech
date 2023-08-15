package com.goktech.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@code @Description:}
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    SCHOOL(1, "在校"),
    DROP(0, "辍学");
    
    @EnumValue
    private Integer status;
    
    private String statusName;
}