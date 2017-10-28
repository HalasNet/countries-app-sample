/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.rest;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Basic integration tests for CountryController.
 * 
 * @author sgrillon
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class CountryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void getCountriesSecuredAuthenticatedThenOk() throws Exception {
        mockMvc.perform(get("/countries/api/v1/countries/all")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(241)));
    }

    @Test
    @WithMockUser
    public void getCountriesEn() throws Exception {
        mockMvc.perform(get("/countries/api/v1/countries/all?lang=en")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(241)));
    }

    @Test
    @WithMockUser
    public void getCountriesFr() throws Exception {
        mockMvc.perform(get("/countries/api/v1/countries/all?lang=fr")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(241)));
    }

}
