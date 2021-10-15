package com.glitterbox.cart.database.repository;

import com.glitterbox.cart.database.entity.CartList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartListRepository extends JpaRepository<CartList,Long> {
}
