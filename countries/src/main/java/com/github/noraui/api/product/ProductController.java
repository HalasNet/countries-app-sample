/**
 * Copyright (c) 2017 NoraUi Oraganization https://github.com/NoraUi/countrie-app-sample
 * All rights reserved.
 * GNU AFFERO GENERAL PUBLIC LICENSE
 */
package com.github.noraui.api.product;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.noraui.model.product.Product;
import com.github.noraui.model.product.ProductResponse;
import com.github.noraui.model.response.OperationResponse;
import com.github.noraui.model.response.ResponseStatus;
import com.github.noraui.repository.ProductRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Products" })
public class ProductController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ProductRepo productRepo;

    @ApiOperation(value = "List of products", response = ProductResponse.class)
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ProductResponse getProductsByPage(@ApiParam(value = "") @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @ApiParam(value = "between 1 to 1000") @RequestParam(value = "size", defaultValue = "20", required = false) Integer size,
            @RequestParam(value = "productid", required = false) Integer productId, @RequestParam(value = "category", required = false) String category, Pageable pageable) {
        ProductResponse resp = new ProductResponse();
        Product qry = new Product();
        if (productId != null) {
            qry.setId(productId);
        }
        if (category != null) {
            qry.setCategory(category);
        }

        Page<Product> productPage = productRepo.findAll(org.springframework.data.domain.Example.of(qry), pageable);
        resp.setPageStats(productPage, true);
        resp.setItems(productPage.getContent());
        return resp;
    }

    @ApiOperation(value = "Add new product", response = OperationResponse.class)
    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = { "application/json" })
    public OperationResponse addNewProduct(@RequestBody Product product, HttpServletRequest req) {

        OperationResponse resp = new OperationResponse();
        System.out.println("ID:" + product.getId());
        System.out.println("Name:" + product.getProductName());
        System.out.println("Code:" + product.getProductCode());
        System.out.println("Category:" + product.getCategory());

        if (this.productRepo.exists(product.getId())) {
            resp.setOperationStatus(ResponseStatus.ERROR);
            resp.setOperationMessage("Unable to add Product - Product allready exist ");
        } else {
            // Product addedProduct = this.productRepo.save(product);
            this.productRepo.save(product);
            resp.setOperationStatus(ResponseStatus.SUCCESS);
            resp.setOperationMessage("Product Added");
        }
        return resp;
    }

    @ApiOperation(value = "Delete a product", response = OperationResponse.class)
    @RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE, produces = { "application/json" })
    public OperationResponse deleteProduct(@PathVariable("productId") Integer productId, HttpServletRequest req) {
        OperationResponse resp = new OperationResponse();
        if (this.productRepo.exists(productId)) {
            this.productRepo.delete(productId);
            resp.setOperationStatus(ResponseStatus.SUCCESS);
            resp.setOperationMessage("Product Deleted");
        } else {
            resp.setOperationStatus(ResponseStatus.ERROR);
            resp.setOperationMessage("No Product Exist");
        }
        return resp;
    }

}
