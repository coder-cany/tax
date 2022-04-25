package com.hs.user.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Company {
    private Integer id;
    private String name;
    private String address;
    private Integer type;
    private LocalDateTime createTime;
}
