package com.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.system.base.BaseEntity;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuhan
 * @since 2023-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_film")
public class Film extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("release_time")
    private LocalDate releaseTime;

    @TableField("category_id")
    private Long categoryId;

    @TableField("region")
    private String region;

    @TableField("cover")
    private String cover;

    @TableField("duration")
    private Integer duration;

    @TableField("grade")
    private BigDecimal grade;


}
