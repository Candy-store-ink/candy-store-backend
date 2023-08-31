package com.github.candy.store.controllers.guest;

import com.github.candy.store.modules.product.Product;
import com.github.candy.store.modules.product.ProductMapper;
import com.github.candy.store.modules.product.ProductService;
import com.github.candy.store.modules.product.payload.GuestProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductControllerGuest implements GuestRequestMapping {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("products")
    public ResponseEntity<Page<GuestProductResponse>> getProducts(@RequestParam(required = false) Long categoryId,
                                                                       @RequestParam(required = false) String search,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "20") int size) {
        Page<Product> products = this.productService.getVisibleProducts(categoryId, search, page, size);
        Page<GuestProductResponse> response = products.map(productMapper::toGuestProductResponse);
        return ResponseEntity.ok(response);
    }
}
