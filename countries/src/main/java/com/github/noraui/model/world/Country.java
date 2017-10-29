/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.model.world;

/**
 * @author sgrillon
 */
public class Country {

    /**
     * Code ISO 3166-1 numerical
     */
    private int num;

    /**
     * Code ISO 3166-1 alpha2
     */
    private String alpha2;

    /**
     * Code ISO 3166-1 alpha3
     */
    private String alpha3;

    /**
     * label of country
     */
    private String label;

    public Country() {
        this.num = 0;
        this.alpha2 = "";
        this.alpha3 = "";
        this.label = "";
    }

    public Country(int num, String alpha2, String alpha3, String label) {
        this.num = num;
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
        this.label = label;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
