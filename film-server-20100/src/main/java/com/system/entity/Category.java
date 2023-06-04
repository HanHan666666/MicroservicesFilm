package com.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.system.base.BaseEntity;
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
@TableName("sys_category")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("url")
    private String url;


}
