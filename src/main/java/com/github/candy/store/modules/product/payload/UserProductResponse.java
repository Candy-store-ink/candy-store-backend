package com.github.candy.store.modules.product.payload;

public record UserProductResponse(
        Long id,
        String name,
        String description,
        String image,
        String category,
        String expiredDate,
        String manufacturer,
        String price,
        boolean isFavorite) {
}
