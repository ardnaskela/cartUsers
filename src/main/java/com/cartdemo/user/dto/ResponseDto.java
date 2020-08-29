package com.cartdemo.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDto implements Serializable {
    private static final long serialVersionUID = 2621578669198343070L;
    private String code;
    private String message;
    private LocalDateTime timestamp;
    private Object body;
}

