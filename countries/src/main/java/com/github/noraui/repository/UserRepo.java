/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.noraui.model.user.User;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findOneByUserId(String userId);

    Optional<User> findOneByUserIdAndPassword(String userId, String password);
}
