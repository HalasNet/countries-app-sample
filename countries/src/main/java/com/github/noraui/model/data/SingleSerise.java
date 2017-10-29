/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.model.data;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SingleSerise {
    private String name;
    private BigDecimal value;

    public SingleSerise(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }
}
