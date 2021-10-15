package com.glitterbox.cart.service;

import com.glitterbox.cart.database.entity.Cart;
import com.glitterbox.cart.database.entity.CartList;
import com.glitterbox.cart.database.repository.CartRepository;
import com.glitterbox.cart.dto.CartDto;
import com.glitterbox.cart.dto.CartListDto;
import com.glitterbox.cart.dto.CartListUpdateDto;
import com.glitterbox.cart.dto.ResponseDto;
import com.glitterbox.cart.exception.CartNotfoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CartServiceimpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ResponseDto responseDto;
    @Override
    public ResponseDto placeCart(CartDto cartDtoList) {
        Cart cart=new Cart();

        AtomicReference<Float> total= new AtomicReference<>((float) 0);
        List<CartList> collect = cartDtoList.getCartLists().stream()
                .map(cartDto -> {
                    CartList cartList = new CartList();
                    BeanUtils.copyProperties(cartDto,cartList);
                    cartList.setCart(cart);
                    return cartList;
                }).collect(Collectors.toList());
        collect.forEach(cartList -> total.updateAndGet(v ->  (v + (cartList.getProductTotalPrice()))));
      log.info("total {}",collect.toString());
      cart.setCartLists(collect);
      cart.setProductTotalPrice(total.get());
      cart.setMobileNo(cartDtoList.getMobileNo());
        log.info("saving cart information for customer mobile no {}",cart.getMobileNo());
      cartRepository.save(cart);

      responseDto.setT(cart.getCartId());
      responseDto.setMessage("cart id generated successfully");
        return responseDto;
    }

    @Override
    public ResponseDto getCart(Long cartId) throws CartNotfoundException {
        log.info("fetching cartId {}",cartId);
        Cart cart = cartRepository.findByCartId(cartId).orElseThrow(() ->  new CartNotfoundException());
        List<CartListDto> cartListDtos = cart.getCartLists().stream().map(cartList -> {
            CartListDto cartListDto = new CartListDto();
            BeanUtils.copyProperties(cartList, cartListDto);
            return cartListDto;
        }).collect(Collectors.toList());

        final CartDto cartDto = CartDto.builder().cartLists(cartListDtos)
                .mobileNo(cart.getMobileNo())
                .total(cart.getProductTotalPrice())
                .build();
        responseDto.setT(cartDto);
        responseDto.setMessage("cart detail found");
        return responseDto;
    }

    @Override
    public ResponseDto updateCart(Long cartId, List<CartListDto> cartListDtos) {
        List<CartList> cartLists=new ArrayList<>();
        List<CartList> cartRemovalLists=new ArrayList<>();
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("cart detail not found"));
//       log.info("cart list {}",cart.getCartLists().toString());
   cart.getCartLists().stream().map(cartList -> {
            cartListDtos.forEach(cartListDto -> {
                if (cartListDto.isExist()) {
                    if (cartListDto.getProductId().equals(cartList.getProductId())) {
                        if (cartList.getProductQuantity() > cartListDto.getProductQuantity()) {
                            cartList.setProductQuantity(cartList.getProductQuantity() - Math.abs(cartList.getProductQuantity() - cartListDto.getProductQuantity()));
                            cartList.setProductTotalPrice(cartList.getProductPrice() * cartList.getProductQuantity());
                        } else if (cartList.getProductQuantity() > cartListDto.getProductQuantity()) {
                            cartList.setProductQuantity(cartList.getProductQuantity() + Math.abs(cartList.getProductQuantity() - cartListDto.getProductQuantity()));
                            cartList.setProductTotalPrice(cartList.getProductPrice() * cartList.getProductQuantity());
                        } else {
                            cartRemovalLists.add(cartList);
//                            cart.getCartLists().remove(cartList);
                        }
                    }
                } else {
                    CartList cartList1 = new CartList();
                    BeanUtils.copyProperties(cartList, cartList);
                    cartList.setCart(cart);
                    cartLists.add(cartList1);

                }
            });
            return cartList;
        }).collect(Collectors.toList());
     cart.getCartLists().remove(cartRemovalLists);
        cart.setCartLists(cartLists);

        cartRepository.save(cart);
        return null;
    }
}
