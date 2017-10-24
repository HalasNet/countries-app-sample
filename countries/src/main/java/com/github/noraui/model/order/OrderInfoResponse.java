package com.github.noraui.model.order;

import io.swagger.annotations.*;
import lombok.*;
import java.util.*;

import com.github.noraui.model.response.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderInfoResponse extends PageResponse {
  @ApiModelProperty(required = true, value = "")
  private List<OrderInfo> items;
}
