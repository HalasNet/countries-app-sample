/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.api.world;

import java.net.MalformedURLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.noraui.model.world.Country;
import com.github.noraui.service.CountryService;

/**
 * @author sgrillon
 */
@Controller
@RequestMapping(value = "/api/v1/countries", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    /**
     * Get all countries.
     * 
     * @param lang (cs:Czech, de:German, en:English, es:Spanish, fr:French, it:Italian and nl:Dutch)
     * @return all countries (Code ISO 3166-1 numerical, Code ISO 3166-1 alpha2, Code ISO 3166-1 alpha3, label of country) in a list.
     * @throws MalformedURLException
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Country> getCountries(@RequestParam(value = "lang", defaultValue = "en") String lang) throws MalformedURLException {
        LOGGER.debug("Get countries:  lang[{}]", lang);
        return countryService.getAll(lang);
    }

}
