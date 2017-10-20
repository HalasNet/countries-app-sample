/*
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.countries.repository;

/**
 * @author sgrillon
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.noraui.countries.model.City;

public interface CityRepository extends JpaRepository<City, String> {
}
