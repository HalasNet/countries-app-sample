package com.github.noraui.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.noraui.model.customer.*;

import java.util.Optional;
import java.util.*;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    public List<Customer> findAll();
    public Page<Customer> findAll(Pageable p);
    Customer save(Customer c);
    void delete(Customer c);
    void delete(Integer id);
    boolean exists( Integer id);


}

