/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.countries.service;

import java.util.List;

import com.github.noraui.countries.model.City;

/**
 * @author sgrillon
 */
public interface CityService {

    /**
     * @return
     */
    List<City> getAll();

}
