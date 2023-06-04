package com.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.system.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Rye
 * @since 2023-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_orders")
public class Orders extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("uid")
    private Long uid;

    /**
     * 拍片的id
     */
    @TableField("aid")
    private Long aid;

    /**
     * 座位号
     */
    @TableField("seats")
    private String seats;

    @TableField("price")
    private BigDecimal price;

    /**
     * 支付时间
     */
    @TableField("pay_at")
    private LocalDateTime payAt;


}
