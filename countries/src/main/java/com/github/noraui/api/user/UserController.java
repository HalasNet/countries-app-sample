/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.api.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.noraui.model.response.OperationResponse;
import com.github.noraui.model.response.ResponseStatus;
import com.github.noraui.model.user.User;
import com.github.noraui.model.user.UserResponse;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Authentication" })
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Gets current user information", response = UserResponse.class)
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = { "application/json" })
    public UserResponse getUserInformation(@RequestParam(value = "name", required = false) String userIdParam, HttpServletRequest req) {

        String loggedInUserId = userService.getLoggedInUserId();

        User user;
        boolean provideUserDetails = false;

        if (Strings.isNullOrEmpty(userIdParam)) {
            provideUserDetails = true;
            user = userService.getLoggedInUser();
        } else if (loggedInUserId.equals(userIdParam)) {
            provideUserDetails = true;
            user = userService.getLoggedInUser();
        } else {
            // Check if the current user is superuser then provide the details of requested user
            provideUserDetails = true;
            user = userService.getUserInfoByUserId(userIdParam);
        }

        UserResponse resp = new UserResponse();
        if (provideUserDetails) {
            resp.setOperationStatus(ResponseStatus.SUCCESS);
        } else {
            resp.setOperationStatus(ResponseStatus.NO_ACCESS);
            resp.setOperationMessage("No Access");
        }
        resp.setData(user);
        return resp;
    }

    @ApiOperation(value = "Add new user", response = OperationResponse.class)
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = { "application/json" })
    public OperationResponse addNewUser(@RequestBody User user, HttpServletRequest req) {
        boolean userAddSuccess = userService.addNewUser(user);
        OperationResponse resp = new OperationResponse();
        if (userAddSuccess == true) {
            resp.setOperationStatus(ResponseStatus.SUCCESS);
            resp.setOperationMessage("User Added");
        } else {
            resp.setOperationStatus(ResponseStatus.ERROR);
            resp.setOperationMessage("Unable to add user");
        }
        return resp;
    }

}
