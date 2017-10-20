/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.countries.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.batik.transcoder.SVGAbstractTranscoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.github.noraui.countries.service.PngContainer;

/**
 * @author sgrillon
 */
@Component
public class PngContainerImpl implements PngContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PngContainerImpl.class);

    private byte[] pngContent;

    private HttpStatus errorCode;

    /**
     * {@inheritDoc}
     */
    @Override
    public void convertSvgToPng(String countryCode, int width, int height) {

        LOGGER.debug("convertSvgToPng : countryCode[{}], width[{}], height[{}]", countryCode, width, height);

        // Create a PNG transcoder
        PNGTranscoder pngTranscoder = new PNGTranscoder();

        // Set transcoding option
        try {
            pngTranscoder.addTranscodingHint(SVGAbstractTranscoder.KEY_WIDTH, (float) width);
            pngTranscoder.addTranscodingHint(SVGAbstractTranscoder.KEY_HEIGHT, (float) height);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Exception caught during the conversion svg to png : illegal arg for width (int >= 0) or height (int >= 0)", e);
            pngContent = null;
            errorCode = HttpStatus.BAD_REQUEST;
            return;
        }

        // Create the transcoder input
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream;
        TranscoderInput input;
        inputStream = classLoader.getResourceAsStream(this.convertCountryCodeToPartialPath(countryCode));
        if (inputStream == null) {
            LOGGER.error("Exception caught during the conversion svg to png : countryCode {} doesn't match", countryCode);
            pngContent = null;
            errorCode = HttpStatus.NO_CONTENT;
            return;
        }
        input = new TranscoderInput(inputStream);

        // Create the transcoder output.
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        TranscoderOutput output = new TranscoderOutput(ostream);

        // Save the image.
        try {
            pngTranscoder.transcode(input, output);
        } catch (TranscoderException e) {
            LOGGER.error("Exception caught during the conversion svg to png", e);
            pngContent = null;
            errorCode = HttpStatus.NOT_FOUND;
            return;
        }

        pngContent = ostream.toByteArray();
        errorCode = HttpStatus.OK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getPngContent() {
        LOGGER.debug("getPngContent");
        return pngContent == null ? null : pngContent.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpStatus getErrorCode() {
        LOGGER.debug("getErrorCode");
        return errorCode;
    }

    private String convertCountryCodeToPartialPath(String countryCode) {
        return "svg/" + countryCode + ".svg";
    }
}
