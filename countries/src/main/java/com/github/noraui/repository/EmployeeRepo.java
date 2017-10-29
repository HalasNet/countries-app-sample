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

import com.github.noraui.model.employee.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    @Override
    public List<Employee> findAll();

    @Override
    public Page<Employee> findAll(Pageable p);

    @Override
    Employee save(Employee e);

    @Override
    void delete(Employee e);

    @Override
    void delete(Integer id);

    @Override
    boolean exists(Integer id);
}
