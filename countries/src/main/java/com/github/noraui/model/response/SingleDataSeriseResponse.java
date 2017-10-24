//This is a common http response model for providing data series

package com.github.noraui.model.response;

import lombok.*;
import java.util.*;

import com.github.noraui.model.data.*;

import io.swagger.annotations.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class SingleDataSeriseResponse extends OperationResponse {
    private List<SingleSerise> items;
}
