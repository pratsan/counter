package com.glitterbox.cart.database.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@ToString
@Table(name = "CART")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", unique = true)
    private Long cartId;
   @Column(name = "mobile_no",nullable = false)
   private String mobileNo;
    @Column(name = "total")
    private float productTotalPrice;
    @Column(name = "status",columnDefinition = "boolean default false")
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "cart")
    private List<CartList> cartLists;


}
