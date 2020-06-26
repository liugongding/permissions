package com.dingding.permissions.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-06-23 22:30:39
 */

@Data
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 404338516307763367L;
    /**
    * 主键id
    */
    private Integer id;
    /**
    * 用户名
    */
    private String username;
    /**
    * 用户密码
    */
    private String password;
    /**
    * 细粒度
    */
    private String perms;


}