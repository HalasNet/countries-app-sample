package com.github.noraui.model.product;

import io.swagger.annotations.*;
import lombok.*;
import java.util.*;

import com.github.noraui.model.response.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductResponse extends PageResponse {
  @ApiModelProperty(required = true, value = "")
  private List<Product> items;
}
