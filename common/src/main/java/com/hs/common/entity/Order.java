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
public class Order {
    private String company;
    private String service;
    private String material;
    private String extra;
    private Integer money;
    private LocalDateTime createTime;
}
