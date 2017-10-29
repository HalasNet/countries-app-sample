/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.identity;

import org.springframework.security.core.authority.AuthorityUtils;

import com.github.noraui.model.user.User;

public class TokenUser extends org.springframework.security.core.userdetails.User {
    private User user;

    // For returning a normal user
    public TokenUser(User user) {
        super(user.getUserId(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getRole() {
        return user.getRole().toString();
    }
}
