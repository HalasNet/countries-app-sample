/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.countries.rest;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class FlagsControllerTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @WithMockUser
    public void getCountriesSecuredAuthenticatedThenOk() throws Exception {
        mockMvc.perform(get("/countries/api/v1/flags/countries"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(241)));
    }
    

    @Test
    @WithMockUser
    public void getCountriesEn() throws Exception {
        mockMvc.perform(get("/countries/api/v1/flags/countries?lang=en"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(241)));
    }

    @Test
    @WithMockUser
    public void getCountriesFr() throws Exception {
        mockMvc.perform(get("/countries/api/v1/flags/countries?lang=fr"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(241)));
    }

    @Test
    @WithMockUser
    public void getSvgFr() throws Exception {
        mockMvc.perform(get("/countries/api/v1/flags/fr"))
        .andExpect(status().isOk())
        .andExpect(content().xml("<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"480\" width=\"640\" version=\"1\"><g fill-rule=\"evenodd\" stroke-width=\"1pt\"><path fill=\"#fff\" d=\"M0 0h640v480H0z\"/><path fill=\"#00267f\" d=\"M0 0h213.337v480H0z\"/><path fill=\"#f31830\" d=\"M426.662 0H640v480H426.662z\"/></g></svg>"));
    }

    @Test
    @WithMockUser
    public void getPngFr() throws Exception {
        byte[] expectedPngContent = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("flags/fr_40_40.png"));
        mockMvc.perform(get("/countries/api/v1/flags/fr/40/40"))
        .andExpect(status().isOk())
        .andExpect(content().bytes(expectedPngContent));
    }

    @Test
    @WithMockUser
    public void getSvgError() throws Exception {
        mockMvc.perform(get("/countries/api/v1/flags/fake"))
        .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    public void getPngError() throws Exception {
        mockMvc.perform(get("/countries/api/v1/flags/fake/40/40"))
        .andExpect(status().isNoContent());
    }

}
