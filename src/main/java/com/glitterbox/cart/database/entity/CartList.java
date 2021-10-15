package com.glitterbox.cart.database.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


@Entity
@Setter
@Getter
@ToString
@Table(name = "cart_list")
public class CartList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_list_id", unique = true)
    private Long cartListId;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "product_barcode")
    private String productBarCode;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_quantity")
    private float productQuantity;
    @Column(name = "product_price")
    private float productPrice;
    @Column(name = "product_total")
    private float productTotalPrice;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
