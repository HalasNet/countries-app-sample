package com.github.noraui.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.noraui.model.order.*;

import java.util.Optional;
import java.util.*;


public interface OrderRepo extends JpaRepository<Order, Integer> {
    public List<Order> findAll();
    public Page<Order> findAll(Pageable p);

}

