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
public class WorkStream {
    private Integer prePerson;
    private Integer aftPerson;
    private Order order;
    private LocalDateTime time;
}
