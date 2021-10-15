package com.glitterbox.cart.database.repository;

import com.glitterbox.cart.database.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public Optional<Cart> findByCartId(Long cartId);
}
