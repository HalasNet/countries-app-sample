/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.countries.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Basic integration tests for FlagsController.
 * 
 * @author sgrillon
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class FlagsControllerTests {

    @LocalServerPort
    private int port;

    @Test
    public void getCountries() throws Exception {
        ResponseEntity<List> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/flags/api/countries", List.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isEqualTo(241);
    }
    
    @Test
    public void getCountriesEn() throws Exception {
        ResponseEntity<List> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/flags/api/countries?lang=en", List.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isEqualTo(241);
    }
    
    @Test
    public void getCountriesFr() throws Exception {
        ResponseEntity<List> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/flags/api/countries?lang=fr", List.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isEqualTo(241);
    }
    
    @Test
    public void getSvgFr() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/flags/api/fr", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isXmlEqualTo("<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"480\" width=\"640\" version=\"1\"><g fill-rule=\"evenodd\" stroke-width=\"1pt\"><path fill=\"#fff\" d=\"M0 0h640v480H0z\"/><path fill=\"#00267f\" d=\"M0 0h213.337v480H0z\"/><path fill=\"#f31830\" d=\"M426.662 0H640v480H426.662z\"/></g></svg>");
    }
    
    @Test
    public void getPngFr() throws Exception {
        byte[] expectedPngContent = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("flags/fr_40_40.png"));
        ResponseEntity<byte[]> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/flags/api/fr/40/40", byte[].class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo(expectedPngContent);
    }
    
    @Test
    public void getSvgError() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/flags/api/fake", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    
    @Test
    public void getPngError() throws Exception {
        ResponseEntity<byte[]> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/flags/api/fake/40/40", byte[].class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
