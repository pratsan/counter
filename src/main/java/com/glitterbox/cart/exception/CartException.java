package com.glitterbox.cart.exception;

import com.glitterbox.cart.dto.ResponseDto;
import com.glitterbox.cart.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class CartException extends ResponseEntityExceptionHandler {
@Autowired
private ResponseDto responseDto;
    @ExceptionHandler(CartNotfoundException.class)
    public ResponseEntity<ResponseDto> cartNotFound(CartNotfoundException cartNotfoundException) {
        responseDto.setMessage(Constant.CART_NOT_FOUND.getMessage());
        responseDto.setT(null);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);

    }

}
