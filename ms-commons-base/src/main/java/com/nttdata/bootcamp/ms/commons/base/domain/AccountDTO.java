package com.nttdata.bootcamp.ms.commons.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDTO {

    private String id;
    private Integer type;
    private BigDecimal commission;
    private Integer movementCount;

}
