/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.api;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.github.noraui.model.response.OperationResponse;
import com.github.noraui.model.response.ResponseStatus;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public OperationResponse handleBaseException(DataIntegrityViolationException e) {
        OperationResponse resp = new OperationResponse();
        resp.setOperationStatus(ResponseStatus.ERROR);
        resp.setOperationMessage(e.getRootCause().getMessage());
        return resp;
    }

}
