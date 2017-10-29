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

import com.github.noraui.model.customer.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    @Override
    public List<Customer> findAll();

    @Override
    public Page<Customer> findAll(Pageable p);

    @Override
    Customer save(Customer c);

    @Override
    void delete(Customer c);

    @Override
    void delete(Integer id);

    @Override
    boolean exists(Integer id);

}
