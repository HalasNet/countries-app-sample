package com.github.noraui.model.user;

import io.swagger.annotations.*;
import lombok.*;
import java.util.*;

import com.github.noraui.model.response.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserResponse extends OperationResponse {
    private User data = new User();
}
