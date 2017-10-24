package com.github.noraui.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import com.github.noraui.model.product.*;

@Transactional
public interface ProductRepo extends JpaRepository<Product, Integer> {
    public List<Product> findAll();
    public Page<Product> findAll(Pageable p);
    Optional<Product> findOneById(Integer id);

    //Product save(Product p);
    //void delete(Product p) ;
    //void delete(Integer id);
    //Product deleteById(Integer id);
    //boolean exists( Integer id);
}

