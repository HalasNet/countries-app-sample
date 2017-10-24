package com.github.noraui.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.noraui.model.order.*;

import java.util.Optional;
import java.util.*;


public interface OrderInfoRepo extends JpaRepository<OrderInfo, Long> {
    public List<OrderInfo> findAll();
    public Page<OrderInfo> findAll(Pageable p);

}

