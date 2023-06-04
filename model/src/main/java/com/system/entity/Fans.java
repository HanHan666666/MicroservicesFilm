package com.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-05-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_fans")
public class Fans extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("email")
    private String email;

    @TableField("sex")
    private Integer sex;

    @TableField("info")
    private String info;

    @TableField("delTag")
    private Integer deltag;

    @TableField(exist = false)
    private String token;

}
