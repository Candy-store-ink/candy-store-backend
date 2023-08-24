package com.github.candy.store.controllers;

import com.github.candy.store.modules.category.Category;
import com.github.candy.store.modules.category.CategoryService;
import com.github.candy.store.modules.product.payload.AdminProductResponse;
import com.github.candy.store.modules.product.payload.GuestProductResponse;
import com.github.candy.store.modules.product.Product;
import com.github.candy.store.modules.product.ProductMapper;
import com.github.candy.store.modules.product.ProductService;
import com.github.candy.store.modules.product.payload.UserProductResponse;
import com.github.candy.store.modules.product.payload.ProductRequest;
import com.github.candy.store.modules.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @GetMapping("guest/products")
    public ResponseEntity<Page<GuestProductResponse>> getGuestProducts(@RequestParam(required = false) Long categoryId,
                                                                       @RequestParam(required = false) String search,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "20") int size) {
        Page<Product> products = this.productService.getVisibleProducts(categoryId, search, page, size);
        Page<GuestProductResponse> response = products.map(productMapper::toGuestProductResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("user/products")
    public ResponseEntity<Page<UserProductResponse>> getUserProducts(@AuthenticationPrincipal User user,
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

    @GetMapping("admin/products")
    public ResponseEntity<Page<AdminProductResponse>> getAdminProducts(@RequestParam(required = false) Long categoryId,
                                                                       @RequestParam(required = false) String search,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "20") int size) {
        Page<Product> products = productService.getProducts(categoryId, search, page, size);
        Page<AdminProductResponse> response = products.map(productMapper::toAdminProductResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping("admin/product")
    public ResponseEntity<AdminProductResponse> createProduct(@RequestPart("body") ProductRequest request,
                                                              @RequestPart(value = "image", required = false) MultipartFile image) {
        Category category = categoryService.getCategory(request.categoryId());
        Product product = productService.createProduct(request, category, image);
        AdminProductResponse response = productMapper.toAdminProductResponse(product);
        return ResponseEntity.ok(response);
    }

}
