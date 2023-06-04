package com.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.system.base.BaseEntity;
import java.time.LocalDate;
import java.time.LocalTime;
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
@TableName("sys_arrangement")
public class Arrangement extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("fid")
    private Long fid;

    @TableField("name")
    private String name;

    @TableField("seat_number")
    private Integer seatNumber;

    @TableField("box_office")
    private Integer boxOffice;

    @TableField("price")
    private BigDecimal price;

    @TableField("type")
    private String type;

    @TableField("date")
    private LocalDate date;

    @TableField("start_time")
    private LocalTime startTime;

    @TableField("end_time")
    private LocalTime endTime;


}
