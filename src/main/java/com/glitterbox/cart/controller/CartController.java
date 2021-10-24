package com.glitterbox.cart.controller;

import com.glitterbox.cart.dto.CartDto;
import com.glitterbox.cart.dto.CartListDto;
import com.glitterbox.cart.dto.CartListUpdateDto;
import com.glitterbox.cart.dto.ResponseDto;
import com.glitterbox.cart.exception.CartNotfoundException;
import com.glitterbox.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/")
    public ResponseEntity<ResponseDto> placeOrder(@RequestBody CartDto cartDtoList) {

        return ResponseEntity.ok(cartService.placeCart(cartDtoList));
    }
    @PutMapping("{cartId}/")
    public ResponseEntity<ResponseDto> updateCart(@PathVariable("cartId") Long cartId, @RequestBody List<CartListDto> cartListDto) {
       return ResponseEntity.ok(cartService.updateCart(cartId,cartListDto));

    }

    @GetMapping("{cartId}/")
    public ResponseEntity<ResponseDto> getCart(@PathVariable("cartId") Long cartId) throws CartNotfoundException {
        return ResponseEntity.ok( cartService.getCart(cartId));
    }
}
