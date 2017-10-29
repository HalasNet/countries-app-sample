/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.service;

import java.net.MalformedURLException;
import java.util.List;

import com.github.noraui.model.world.Country;

/**
 * @author sgrillon
 */
public interface CountryService {

    /**
     * Get all countries.
     * 
     * @param lang: language (cs:Czech, de:German, en:English, es:Spanish, fr:French, it:Italian and nl:Dutch)
     * @return all counties (Code ISO 3166-1 numerical, Code ISO 3166-1 alpha2, Code ISO 3166-1 alpha3, label of country) in a list.
     * @throws MalformedURLException
     */
    List<Country> getAll(String lang) throws MalformedURLException;

}
