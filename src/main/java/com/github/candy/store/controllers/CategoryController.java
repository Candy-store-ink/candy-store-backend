package com.github.candy.store.controllers;

import com.github.candy.store.modules.category.Category;
import com.github.candy.store.modules.category.CategoryMapper;
import com.github.candy.store.modules.category.CategoryResponse;
import com.github.candy.store.modules.category.CategoryService;
import com.github.candy.store.modules.category.payload.CategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("guest/categories")
    public ResponseEntity<Page<CategoryResponse>> getGuestCategories(@RequestParam(required = false) String search,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "20") int size) {
        Page<Category> categories = this.categoryService.getCategories(search, page, size);
        Page<CategoryResponse> response = categories.map(categoryMapper::toCategoryResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("user/categories")
    public ResponseEntity<Page<CategoryResponse>> getUserCategories(@RequestParam(required = false) String search,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "20") int size) {
        Page<Category> categories = this.categoryService.getCategories(search, page, size);
        Page<CategoryResponse> response = categories.map(categoryMapper::toCategoryResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("admin/categories")
    public ResponseEntity<Page<CategoryResponse>> getAdminCategories(@RequestParam(required = false) String search,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "20") int size) {
        Page<Category> categories = this.categoryService.getCategories(search, page, size);
        Page<CategoryResponse> response = categories.map(categoryMapper::toCategoryResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "admin/category", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> createCategory(@RequestPart("body") CategoryRequest request,
                                                           @RequestPart(value = "image", required = false) MultipartFile image) {
        Category category = this.categoryService.createCategory(request, image);
        CategoryResponse response = this.categoryMapper.toCategoryResponse(category);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("admin/category")
    public void deleteCategory(@RequestParam long id) {
        this.categoryService.deleteCategory(id);
    }

}
