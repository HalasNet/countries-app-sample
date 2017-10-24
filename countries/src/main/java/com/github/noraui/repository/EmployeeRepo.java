package com.github.noraui.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.noraui.model.employee.*;

import java.util.Optional;
import java.util.*;


public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    public List<Employee> findAll();
    public Page<Employee> findAll(Pageable p);
    Employee save(Employee e);
    void delete(Employee e);
    void delete(Integer id);
    boolean exists( Integer id);
}

