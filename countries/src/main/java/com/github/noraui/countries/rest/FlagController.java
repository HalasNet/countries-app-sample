/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.countries.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.noraui.countries.service.FlagService;
import com.github.noraui.countries.service.PngContainer;

/**
 * @author sgrillon
 */
@Controller
@RequestMapping("/countries/api/v1/flags")
public class FlagController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlagController.class);

    @Autowired
    private FlagService flagService;

    /**
     * @param countryAlpha2Code
     * @return content of SVG picture (String)
     */
    @RequestMapping(value = "/{countryAlpha2Code}", method = RequestMethod.GET, produces = "image/svg+xml")
    public ResponseEntity<String> getSvgFlag(@PathVariable String countryAlpha2Code) {
        LOGGER.debug("getSvgFlag : countryAlpha2Code[{}]", countryAlpha2Code);
        String flag = flagService.getSvgFlag(countryAlpha2Code);
        return Optional.ofNullable(flag).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    /**
     * @param countryAlpha2Code
     * @return picture of flag (png file)
     */
    @RequestMapping(value = "/{countryAlpha2Code}/{width}/{height}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getPngFlag(@PathVariable String countryAlpha2Code, @PathVariable int width, @PathVariable int height) {
        LOGGER.debug("getSvgFlag : countryAlpha2Code[{}], width[{}], height[{}]", countryAlpha2Code, width, height);
        PngContainer pngContainer = flagService.getPngFlag(countryAlpha2Code, width, height);
        return Optional.ofNullable(pngContainer.getPngContent()).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
