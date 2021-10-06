package com.hs.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private int id;
    transient private String password;
    private String name;
    private Integer role;
    private String idCard;
    private Integer tel;
    private String email;
    private Integer sex;
    private LocalDateTime birthday;
    private String img;
    private LocalDateTime lastLoginTime;
    private LocalDateTime createTime;
}
