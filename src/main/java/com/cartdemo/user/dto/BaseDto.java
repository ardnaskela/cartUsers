package com.cartdemo.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class BaseDto implements Serializable {
    private static final long serialVersionUID = 5634066746244045321L;
    protected Long id;
}
