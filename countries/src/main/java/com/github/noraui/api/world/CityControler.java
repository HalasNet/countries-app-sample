/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.api.world;

import java.util.List;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.noraui.model.world.City;
import com.github.noraui.service.CityService;

/**
 * @author sgrillon
 */
@Controller
@RequestMapping(value = "/api/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityControler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityControler.class);

    private static final PolicyFactory stripHtml = new HtmlPolicyBuilder().toFactory();

    @Autowired
    private CityService cityService;

    /**
     * Get all cities.
     * 
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<City> getCities() {
        LOGGER.debug("Received request to list all cities");
        return cityService.getAll();
    }

    @RequestMapping(value = "/{countryAlpha2Code}", method = RequestMethod.GET)
    public @ResponseBody List<City> getCitiesByIso2(@PathVariable String countryAlpha2Code) {
        LOGGER.debug("Received request to list all cities of {}", countryAlpha2Code);
        countryAlpha2Code = sanitize(countryAlpha2Code);
        return cityService.getAll();
    }

    @RequestMapping(value = "/{countryAlpha2Code}/switch/{cityId1}/{cityId2}", method = RequestMethod.PATCH, consumes = MediaType.ALL_VALUE,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<City>> switchCitiesOrder(@PathVariable String countryAlpha2Code, @PathVariable String cityId1, @PathVariable String cityId2) {
        LOGGER.debug("writeValue : switch[{}{}{}]", countryAlpha2Code, cityId1, cityId2);
        countryAlpha2Code = sanitize(countryAlpha2Code);
        cityId1 = sanitize(cityId1);
        cityId2 = sanitize(cityId2);
        return ResponseEntity.ok().body(cityService.getAll());
    }

    private String sanitize(String input) {
        if (input == null) {
            return null;
        }
        String res = stripHtml.sanitize(input);
        if (res.isEmpty()) {
            return null;
        }
        return res;
    }

}
