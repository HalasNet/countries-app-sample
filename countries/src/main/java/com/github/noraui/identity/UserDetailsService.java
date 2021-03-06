/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.noraui.model.user.User;
import com.github.noraui.repository.UserRepo;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public final TokenUser loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException {

        final User user = userRepo.findOneByUserId(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        TokenUser currentUser;
        if (user.isActive() == true) {
            currentUser = new TokenUser(user);
        } else {
            throw new DisabledException("User is not activated (Disabled User)");
            // If pending activation return a disabled user
            // currentUser = new TokenUser(user, false);
        }
        detailsChecker.check(currentUser);
        return currentUser;
    }
}
