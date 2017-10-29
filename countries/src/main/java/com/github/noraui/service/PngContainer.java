/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.service;

import org.springframework.http.HttpStatus;

/**
 * @author sgrillon
 */
public interface PngContainer {

    /**
     * Convert a SVG picture to PNG.
     * 
     * @param countryCode (country code -> example France : "fr")
     * @param width
     * @param height
     */
    void convertSvgToPng(String countryCode, int width, int height);

    /**
     * Return content of PNG picture.
     * 
     * @return pngContent
     */
    byte[] getPngContent();

    /**
     * Return error code of PNG picture (200 : no error, 400 : height or width less than or equal to 0, 404 : Country code does not exist)
     * 
     * @return errorCode
     */
    HttpStatus getErrorCode();

}
