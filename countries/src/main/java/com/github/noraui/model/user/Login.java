/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Login {

    @ApiModelProperty(example = "demo", required = true)
    private String username = "";

    @ApiModelProperty(example = "demo", required = true)
    private String password = "";

}
