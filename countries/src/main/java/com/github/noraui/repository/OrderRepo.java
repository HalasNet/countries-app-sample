/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.noraui.model.order.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Override
    public List<Order> findAll();

    @Override
    public Page<Order> findAll(Pageable p);

}
