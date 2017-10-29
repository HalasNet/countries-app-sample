/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.github.noraui.model.product.Product;

@Transactional
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Override
    public List<Product> findAll();

    @Override
    public Page<Product> findAll(Pageable p);

    Optional<Product> findOneById(Integer id);

}
