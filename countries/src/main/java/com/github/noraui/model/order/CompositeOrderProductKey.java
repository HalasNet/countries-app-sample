/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.model.order;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class CompositeOrderProductKey implements Serializable {
    private int orderId;
    private int productId;

    public CompositeOrderProductKey(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
}
