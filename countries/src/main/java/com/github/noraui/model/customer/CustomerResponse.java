package com.github.noraui.model.customer;

import io.swagger.annotations.*;
import lombok.*;
import java.util.*;

import com.github.noraui.model.response.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerResponse extends PageResponse {
  @ApiModelProperty(required = true, value = "")
  private List<Customer> items;
}
