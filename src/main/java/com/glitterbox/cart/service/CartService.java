package com.glitterbox.cart.service;

import com.glitterbox.cart.dto.CartDto;
import com.glitterbox.cart.dto.CartListDto;
import com.glitterbox.cart.dto.CartListUpdateDto;
import com.glitterbox.cart.dto.ResponseDto;
import com.glitterbox.cart.exception.CartNotfoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {
    public ResponseDto placeCart(CartDto cartDtoList);
    public ResponseDto getCart(Long cartId) throws CartNotfoundException;
    public ResponseDto updateCart(Long cartId, List<CartListDto> cartListDtos);
}
