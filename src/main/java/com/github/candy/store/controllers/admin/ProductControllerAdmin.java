package com.github.candy.store.controllers.admin;

import com.github.candy.store.modules.category.Category;
import com.github.candy.store.modules.category.CategoryService;
import com.github.candy.store.modules.product.Product;
import com.github.candy.store.modules.product.ProductMapper;
import com.github.candy.store.modules.product.ProductService;
import com.github.candy.store.modules.product.payload.AdminProductResponse;
import com.github.candy.store.modules.product.payload.ProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductControllerAdmin implements AdminRequestMapping {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @GetMapping("products")
    public ResponseEntity<Page<AdminProductResponse>> getProducts(@RequestParam(required = false) Long categoryId,
                                                                  @RequestParam(required = false) String search,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "20") int size) {
        Page<Product> products = productService.getProducts(categoryId, search, page, size);
        Page<AdminProductResponse> response = products.map(productMapper::toAdminProductResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "product",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminProductResponse> createProduct(@RequestPart("body") ProductRequest request,
                                                              @RequestPart(required = false) MultipartFile image) {
        Category category = categoryService.getCategory(request.categoryId());
        Product product = productService.createProduct(request, category, image);
        AdminProductResponse response = productMapper.toAdminProductResponse(product);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "product",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminProductResponse> updateProduct(@RequestParam long id,
                                                              @RequestPart("body") ProductRequest request,
                                                              @RequestPart(required = false) MultipartFile image) {
        Category category = categoryService.getCategory(request.categoryId());
        Product product = productService.updateProduct(id, request, category, image);
        AdminProductResponse response = productMapper.toAdminProductResponse(product);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("product")
    public void deleteProduct(@RequestParam long id) {
        productService.deleteProduct(id);
    }
}
