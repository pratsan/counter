package com.glitterbox.cart.dto;

import lombok.Data;

@Data
public class CartListDto  {
    private String productId;
    private String productBarCode;
    private String productName;
    private float productQuantity;
    private float productPrice;
    private float productTotalPrice;
    private boolean exist;

}
