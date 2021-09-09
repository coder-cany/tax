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
    private int account;
    private String password;
    private String name;
    private Integer role;
    private Integer sex;
    private LocalDateTime createTime;
    private String img;
}
