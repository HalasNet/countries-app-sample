/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.noraui.service.ConverterService;
import com.github.noraui.service.FlagService;
import com.github.noraui.service.PngContainer;

/**
 * @author sgrillon
 */
@Component
public class FlagServiceImpl implements FlagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlagServiceImpl.class);

    @Autowired
    private ConverterService converterService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSvgFlag(String countryAlpha2Code) {
        LOGGER.debug("getSvgFlag : countryAlpha2Code[{}]", countryAlpha2Code);
        return this.getSvgContent(countryAlpha2Code);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PngContainer getPngFlag(String countryAlpha2Code, int width, int height) {
        LOGGER.debug("getPngFlag : countryAlpha2Code[{}], width[{}], height[{}]", countryAlpha2Code, width, height);
        return converterService.svg2png(countryAlpha2Code, width, height);
    }

    protected String getSvgContent(String countryAlpha2Code) {
        LOGGER.debug("getSvgContent : countryAlpha2Code[{}]", countryAlpha2Code);
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream input = classLoader.getResourceAsStream(this.convertCountryCodeToPartialPath(countryAlpha2Code));
        try {
            if (input == null) {
                return null;
            }
            byte[] encoded = IOUtils.toByteArray(input);
            input.close();
            return new String(encoded, "UTF-8");
        } catch (IOException e) {
            LOGGER.error("Exception caught during getting svg content", e);
            return null;
        }
    }

    private String convertCountryCodeToPartialPath(String countryCode) {
        return "svg/" + countryCode + ".svg";
    }

}
