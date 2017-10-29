/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.noraui.service.ConverterService;
import com.github.noraui.service.PngContainer;

/**
 * @author sgrillon
 */
@Component
public class ConverterServiceImpl implements ConverterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterServiceImpl.class);

    @Autowired
    private PngContainer pngContainer;

    /**
     * {@inheritDoc}
     */
    @Override
    public PngContainer svg2png(String countryCode, int width, int height) {
        LOGGER.debug("svg2png : countryCode[{}], width[{}], height[{}]", countryCode, width, height);
        pngContainer.convertSvgToPng(countryCode, width, height);
        return pngContainer;
    }
}
