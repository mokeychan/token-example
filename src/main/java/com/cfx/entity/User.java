package com.cfx.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @Author: chenfeixiang
 * @Date: Created in 18:06 2018/9/11
 */
@Entity
@Table( name = "user_")
@Data
public class User implements Serializable {
    @Id
    @Column(name = "id_")
    private Long id;

    @Column(name = "username_")
    private String username;

    @Column(name = "password_")
    private String password;

    @Column(name = "nickname_")
    private String nickname;
}
