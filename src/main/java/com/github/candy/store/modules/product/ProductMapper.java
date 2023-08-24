package com.github.candy.store.modules.product;

import com.github.candy.store.modules.product.payload.AdminProductResponse;
import com.github.candy.store.modules.product.payload.GuestProductResponse;
import com.github.candy.store.modules.product.payload.UserProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public GuestProductResponse toGuestProductResponse(Product product) {
        return new GuestProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getCategory().getName(),
                product.getExpirationDate().toString(),
                product.getManufacturer()
        );
    }

    public UserProductResponse toUserProductResponse(Product product, boolean isFavorite) {
        return new UserProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getCategory().getName(),
                product.getExpirationDate().toString(),
                product.getManufacturer(),
                product.getPrice().toString(),
                isFavorite
        );
    }

    public AdminProductResponse toAdminProductResponse(Product product) {
        return new AdminProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getCategory().getName(),
                product.getExpirationDate().toString(),
                product.getManufacturer(),
                product.getPrice().toString(),
                product.isVisible()
        );
    }
}
