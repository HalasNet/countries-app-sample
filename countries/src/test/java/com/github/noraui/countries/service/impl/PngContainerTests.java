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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.noraui.countries.service.PngContainer;

/**
 * Basic unit tests for PngContainerTests.
 * 
 * @author sgrillon
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PngContainerTests {
    
    @Autowired
    private PngContainer pngContainer;
    
    @Test
    public void contexLoads() throws Exception {
        assertThat(pngContainer).isNotNull();
    }

    @Test
    public void testGetSvgContentWithFr() throws IOException {
        byte[] expectedPngContent = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("flags/fr_40_40.png"));
        pngContainer.convertSvgToPng("fr", 40, 40);
        assertThat(expectedPngContent.equals(pngContainer.getPngContent()));
        assertThat(pngContainer.getErrorCode() == HttpStatus.OK);
    }

}
