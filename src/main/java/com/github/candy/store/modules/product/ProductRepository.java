package com.github.candy.store.modules.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);

    Page<Product> findAllByCategoryIdAndVisibleTrue(Long categoryId, Pageable pageable);
}
