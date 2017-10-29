/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.model;

import lombok.Data;

@Data // For Getters and Setters
public class VersionModel {
    private String version = null;
    private Integer major = null;
    private Integer minor = null;
    private Integer patch = null;
}
