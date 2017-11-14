/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.github.noraui.identity.TokenUtil;
import com.github.noraui.model.response.ResponseStatus;
import com.github.noraui.model.session.SessionResponse;
import com.github.noraui.model.user.Login;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CountriesApplicationTests {

    private static final long VALIDITY_TIME_MS = 2 * 60 * 60 * 1000; // 2 hours validity
    private static final String AUTH_HEADER_NAME = "Authorization";

    private String secret = "mrin";

    private TokenUtil tokenUtil;

    @LocalServerPort
    private int port;

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        HttpEntity<Login> request = new HttpEntity<>(new Login("demo", "demo"), headers);
        ResponseEntity<String> response = new TestRestTemplate().postForEntity("http://localhost:" + port + "/session", request, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Gson gson = new Gson();
        SessionResponse sr = gson.fromJson(response.getBody(), SessionResponse.class);
        Assert.assertEquals(ResponseStatus.SUCCESS, sr.getOperationStatus());
        Assert.assertEquals("Login Success", sr.getOperationMessage());
        Assert.assertEquals("demo", sr.getItem().getUserId());
        Assert.assertEquals("Zinedine", sr.getItem().getFirstName());
        Assert.assertEquals("Zidane", sr.getItem().getLastName());
        Assert.assertEquals("zzidane@mail.com", sr.getItem().getEmail());
        Assert.assertNull(sr.getItem().getRoles());
    }
    //
    // @Test
    // public void loginWithInvalidUserThenUnauthenticated() throws Exception {
    // FormLoginRequestBuilder login = formLogin().user("invalid").password("invalidpassword");
    // mockMvc.perform(login).andExpect(unauthenticated());
    // }
    //
    // @Test
    // public void accessUnsecuredResourceThenOk() throws Exception {
    // mockMvc.perform(get("/")).andExpect(status().isOk());
    // }
    //
    // @Test
    // public void accessSecuredResourceUnauthenticatedThenRedirectsToLogin() throws Exception {
    // mockMvc.perform(get("/hello")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrlPattern("**/login"));
    // }

    @Test
    public void accessSecuredResourceAuthenticatedThenOk() throws Exception {
        String jwt = Jwts.builder().setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS)).setSubject("demo").claim("userId", 1).claim("role", "USER")
                .signWith(SignatureAlgorithm.HS256, secret).compact();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", jwt);

        ResponseEntity<String> entity = new TestRestTemplate().exchange("http://localhost:" + port + "/hello", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

}
