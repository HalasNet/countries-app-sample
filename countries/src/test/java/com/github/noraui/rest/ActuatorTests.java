/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Basic integration tests for FlagsController.
 * 
 * @author sgrillon
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class ActuatorTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = { "SUPERUSER" })
    public void getHealth() throws Exception {
        // mockMvc.perform(get("/health"))
        // .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = { "USER" })
    public void getHealthForbidden() throws Exception {
        // mockMvc.perform(get("/health"))
        // .andExpect(status().isForbidden());
    }

}
