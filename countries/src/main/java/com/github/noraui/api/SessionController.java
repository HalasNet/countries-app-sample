/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.noraui.model.response.ResponseStatus;
import com.github.noraui.model.session.SessionItem;
import com.github.noraui.model.session.SessionResponse;
import com.github.noraui.model.user.Login;
import com.github.noraui.model.user.User;
import com.github.noraui.repository.UserRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/*
 * This is a dummy rest controller, for the purpose of documentation (/session) path is map to a filter
 * - This will only be invoked if security is disabled
 * - If Security is enabled then SessionFilter.java is invoked
 * - Enabling and Disabling Security is done at config/applicaton.properties 'security.ignored=/**'
 */

@RestController
@Api(tags = { "Authentication" })
public class SessionController {

    @Autowired
    private UserRepo userRepo;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "Will return a security token, which must be passed in every request", response = SessionResponse.class) })
    @RequestMapping(value = "/session", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SessionResponse newSession(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response) {
        System.out.format("\n /Session Called username=%s\n", login.getUsername());
        User user = userRepo.findOneByUserIdAndPassword(login.getUsername(), login.getPassword()).orElse(null);
        SessionResponse resp = new SessionResponse();
        SessionItem sessionItem = new SessionItem();
        if (user != null) {
            System.out.format("\n /User Details=%s\n", user.getFirstName());
            sessionItem.setToken("xxx.xxx.xxx");
            sessionItem.setUserId(user.getUserId());
            sessionItem.setFirstName(user.getFirstName());
            sessionItem.setLastName(user.getLastName());
            sessionItem.setEmail(user.getEmail());
            // sessionItem.setRole(user.getRole());

            resp.setOperationStatus(ResponseStatus.SUCCESS);
            resp.setOperationMessage("Dummy Login Success");
            resp.setItem(sessionItem);
        } else {
            resp.setOperationStatus(ResponseStatus.ERROR);
            resp.setOperationMessage("Login Failed");
        }
        return resp;
    }

}
