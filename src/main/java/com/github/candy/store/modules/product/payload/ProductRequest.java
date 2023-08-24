package com.github.candy.store.modules.product.payload;

public record ProductRequest(
        String name,
        String description,
        Long categoryId,
        String expiredDate,
        String manufacturer,
        String price,
        boolean isVisible) {
}
