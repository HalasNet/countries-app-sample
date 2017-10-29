/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.model.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @EmbeddedId
    @AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "order_id")), @AttributeOverride(name = "productId", column = @Column(name = "product_id")) })
    CompositeOrderProductKey orderItemKey;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;

    @ApiModelProperty(allowableValues = "On Order, Allocated, No Stock")
    private String orderItemStatus;
    private Date dateAllocated;

    public OrderItem() {
    }

    // To Create orderitem by passing product_id and order_id as seperate variables
    public OrderItem(int orderId, int productId, BigDecimal quantity, BigDecimal unitPrice, BigDecimal discount, String orderItemStatus, Date dateAllocated) {
        this(new CompositeOrderProductKey(orderId, productId), quantity, unitPrice, discount, orderItemStatus, dateAllocated);
    }

    // TO Create orderitem by passing Composite Key
    public OrderItem(CompositeOrderProductKey orderItemKey, BigDecimal quantity, BigDecimal unitPrice, BigDecimal discount, String orderItemStatus, Date dateAllocated) {
        this.orderItemKey = orderItemKey;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.dateAllocated = dateAllocated;
        this.orderItemStatus = orderItemStatus;
    }

}
