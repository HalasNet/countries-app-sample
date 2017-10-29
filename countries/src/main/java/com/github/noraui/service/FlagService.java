/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.service;

/**
 * @author sgrillon
 */
public interface FlagService {

    /**
     * @param countryAlpha2Code
     * @return content of SVG picture (String)
     */
    String getSvgFlag(String countryAlpha2Code);

    /**
     * @param countryAlpha2Code
     * @param width
     * @param height
     * @return container (content + error code).
     */
    PngContainer getPngFlag(String countryAlpha2Code, int width, int height);
}
