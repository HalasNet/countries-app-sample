/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.countries.service;

import java.net.MalformedURLException;
import java.util.List;

import com.github.noraui.countries.model.Country;

/**
 * 
 * @author sgrillon
 *
 */
public interface CountryService {

    /**
     * 
     * @param lang: language (en: English, fr: Français)
     * @return all counties
     * @throws MalformedURLException
     */
    List<Country> getAll(String lang) throws MalformedURLException;
    
}
