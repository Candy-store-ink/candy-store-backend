package com.github.candy.store.controllers.user;

import com.github.candy.store.modules.product.Product;
import com.github.candy.store.modules.product.ProductMapper;
import com.github.candy.store.modules.product.ProductService;
import com.github.candy.store.modules.product.payload.UserProductResponse;
import com.github.candy.store.modules.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductControllerUser implements UserRequestMapping {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("products")
    public ResponseEntity<Page<UserProductResponse>> getProducts(@AuthenticationPrincipal User user,
                                                                     @RequestParam(required = false) Long categoryId,
                                                                     @RequestParam(required = false) String search,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "20") int size) {
        Page<Product> products = productService.getVisibleProducts(categoryId, search, page, size);
        Page<UserProductResponse> response = products.map(product -> {
            boolean isFavorite = productService.isFavorite(product, user);
            return productMapper.toUserProductResponse(product, isFavorite);
        });
        return ResponseEntity.ok(response);
    }
}
