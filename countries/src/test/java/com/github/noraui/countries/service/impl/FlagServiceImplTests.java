/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.countries.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import com.github.noraui.countries.service.FlagService;
import com.github.noraui.countries.service.PngContainer;

/**
 * Basic unit tests for FlagService.
 * 
 * @author sgrillon
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class FlagServiceImplTests {

    @Autowired
    private FlagService flagService;

    @Test
    public void testGetSvgContentWithFr() {
        String expectedSvgContent = "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"480\" width=\"640\" version=\"1\"><g fill-rule=\"evenodd\" stroke-width=\"1pt\"><path fill=\"#fff\" d=\"M0 0h640v480H0z\"/><path fill=\"#00267f\" d=\"M0 0h213.337v480H0z\"/><path fill=\"#f31830\" d=\"M426.662 0H640v480H426.662z\"/></g></svg>";
        String actualSvgContent = flagService.getSvgFlag("fr");
        assertThat(actualSvgContent).isXmlEqualTo(expectedSvgContent);
    }
    
    @Test
    public void testGetPngContentWithFr() throws IOException {
        byte[] expectedPngContent = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("flags/fr_40_40.png"));
        PngContainer actualPngContent = flagService.getPngFlag("fr", 40, 40);
        System.out.println("sgr code " + actualPngContent.getErrorCode());
        assertThat(actualPngContent.getErrorCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualPngContent.getPngContent()).isEqualTo(expectedPngContent);
    }
    
}
