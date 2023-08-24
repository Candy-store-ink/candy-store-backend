package com.github.candy.store.modules.product;

import com.github.candy.store.modules.category.Category;
import com.github.candy.store.modules.product.payload.ProductRequest;
import com.github.candy.store.modules.user.User;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    Page<Product> getProducts(@Nullable Long categoryId, @Nullable String search, int page, int size);

    Page<Product> getVisibleProducts(@Nullable Long categoryId, @Nullable String search, int page, int size);

    boolean isFavorite(Product product, User user);

    Product createProduct(ProductRequest request, Category category, @Nullable MultipartFile image);
}
