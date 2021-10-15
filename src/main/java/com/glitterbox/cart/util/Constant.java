package com.glitterbox.cart.util;

public enum Constant {
    CART_NOT_FOUND("cart not found");
    private String message;

   private Constant(String message) {
        this.message=message;
    }

    public String getMessage() {
        return this.message;
    }
}
