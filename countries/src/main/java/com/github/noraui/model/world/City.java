/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.model.world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author sgrillon
 */
@Entity
public class City {

    @Id
    @NotNull
    @Size(max = 5)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(max = 12)
    @Column(name = "lat", nullable = false)
    private String lat;

    @NotNull
    @Size(max = 12)
    @Column(name = "lng", nullable = false)
    private String lng;

    @NotNull
    @Size(max = 2)
    @Column(name = "iso2", nullable = false)
    private String iso2;

    @NotNull
    @Size(max = 3)
    @Column(name = "iso3", nullable = false)
    private String iso3;

    @NotNull
    @Size(max = 2)
    @Column(name = "cityorder", nullable = false)
    private int cityorder;

    private City() {
    }

    public City(String id, String name, String lat, String lng, String iso2, String iso3, int cityorder) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.cityorder = cityorder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public int getCityorder() {
        return cityorder;
    }

    public void setCityorder(int cityorder) {
        this.cityorder = cityorder;
    }

}
