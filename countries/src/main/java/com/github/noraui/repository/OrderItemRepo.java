package com.github.noraui.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.noraui.model.order.*;

import java.util.Optional;
import java.util.*;


public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findAll();
    public Page<OrderItem> findAll(Pageable p);
}

