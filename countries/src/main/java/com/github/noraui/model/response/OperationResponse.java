/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
/**
 * This is the common structure for all responses
 * if the response contains a list(array) then it will have 'items' field
 * if the response contains a single item then it will have 'item' field
 */
package com.github.noraui.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data // for getters and setters
public class OperationResponse {

    @ApiModelProperty(required = true)
    private ResponseStatus operationStatus;
    private String operationMessage;

}