/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.api.customer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.noraui.model.customer.Customer;
import com.github.noraui.model.customer.CustomerResponse;
import com.github.noraui.model.response.OperationResponse;
import com.github.noraui.model.response.ResponseStatus;
import com.github.noraui.repository.CustomerRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Transactional
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Customers" })
public class CustomerController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CustomerRepo customerRepo;

    @ApiOperation(value = "List of customers", response = CustomerResponse.class)
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public CustomerResponse getCustomersByPage(@ApiParam(value = "") @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @ApiParam(value = "between 1 to 1000") @RequestParam(value = "size", defaultValue = "20", required = false) Integer size,
            @RequestParam(value = "customerid", required = false) Integer customerId, @RequestParam(value = "company", required = false) String company,
            @RequestParam(value = "country", required = false) String country, Pageable pageable) {
        CustomerResponse resp = new CustomerResponse();
        Customer qry = new Customer();
        if (customerId != null) {
            qry.setId(customerId);
        }
        if (company != null) {
            qry.setCompany(company);
        }
        if (country != null) {
            qry.setCountry(country);
        }

        Page<Customer> pg = customerRepo.findAll(org.springframework.data.domain.Example.of(qry), pageable);
        resp.setPageStats(pg, true);
        resp.setItems(pg.getContent());
        return resp;
    }

    @ApiOperation(value = "Add new customer", response = OperationResponse.class)
    @RequestMapping(value = "/customers", method = RequestMethod.POST, produces = { "application/json" })
    public OperationResponse addNewCustomer(@RequestBody Customer customer, HttpServletRequest req) {
        OperationResponse resp = new OperationResponse();
        if (this.customerRepo.exists(customer.getId())) {
            resp.setOperationStatus(ResponseStatus.ERROR);
            resp.setOperationMessage("Unable to add Customer - Customer allready exist ");
        } else {
            Customer addedCustomer = this.customerRepo.save(customer);
            resp.setOperationStatus(ResponseStatus.SUCCESS);
            resp.setOperationMessage("Customer Added");
        }
        return resp;
    }

    @ApiOperation(value = "Delete a customer", response = OperationResponse.class)
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE, produces = { "application/json" })
    public OperationResponse deleteCustomer(@PathVariable("customerId") Integer customerId, HttpServletRequest req) {
        OperationResponse resp = new OperationResponse();
        try {
            if (this.customerRepo.exists(customerId)) {
                this.customerRepo.delete(customerId);
                resp.setOperationStatus(ResponseStatus.SUCCESS);
                resp.setOperationMessage("Customer Deleted");
            } else {
                resp.setOperationStatus(ResponseStatus.ERROR);
                resp.setOperationMessage("No Customer Exist");
            }
        } catch (Exception ge) {
            System.out.println("========= MRIN GENERIC EXCEPTION ============");
            resp.setOperationStatus(ResponseStatus.ERROR);
            resp.setOperationMessage(ge.getMessage());
        }

        return resp;
    }

}
