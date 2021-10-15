package com.glitterbox.cart.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private T t;
    private String message;
}
