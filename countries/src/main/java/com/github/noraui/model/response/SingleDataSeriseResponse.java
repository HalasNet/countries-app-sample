/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
// This is a common http response model for providing data series
package com.github.noraui.model.response;

import java.util.List;

import com.github.noraui.model.data.SingleSerise;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SingleDataSeriseResponse extends OperationResponse {
    private List<SingleSerise> items;
}
