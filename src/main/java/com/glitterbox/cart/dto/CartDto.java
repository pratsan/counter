package com.glitterbox.cart.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartDto {
    private List<CartListDto> cartLists;
    private String mobileNo;
  private float total;

}
