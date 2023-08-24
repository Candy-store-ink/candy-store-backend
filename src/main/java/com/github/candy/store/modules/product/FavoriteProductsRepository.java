package com.github.candy.store.modules.product;

import com.github.candy.store.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteProductsRepository extends JpaRepository<FavoriteProducts, Long> {
    boolean existsByProductAndUser(Product product, User user);
}
