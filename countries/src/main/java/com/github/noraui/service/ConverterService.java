/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.service;

/**
 * @author sgrillon
 */
public interface ConverterService {

    /**
     * Convert a SVG picture to PNG.
     * 
     * @param countryCode
     * @param width
     * @param height
     * @return container (content + error code).
     */
    PngContainer svg2png(String countryCode, int width, int height);
}
