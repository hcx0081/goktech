package com.goktech.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName gkstudent
 */
@TableName(value = "gkstudent")
@Data
public class Gkstudent implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 学号
     */
    @TableId
    private Long no;
    /**
     * 身份证号
     */
    private Long id;
    /**
     * 报道时间
     */
    private LocalDateTime reportTime;
    /**
     * 照片
     */
    private String photo;
    /**
     * 状态
     */
    private StatusEnum status;
}