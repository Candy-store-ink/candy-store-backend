package com.github.candy.store.controllers.admin;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/")
public class CategoryControllerAdmin {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("categories")
    public ResponseEntity<Page<CategoryResponse>> getCategories(@RequestParam(required = false) String search,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "20") int size) {
        Page<Category> categories = this.categoryService.getCategories(search, page, size);
        Page<CategoryResponse> response = categories.map(categoryMapper::toCategoryResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "category",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> createCategory(@RequestPart("body") CategoryRequest request,
                                                           @RequestPart(required = false) MultipartFile image) {
        Category category = this.categoryService.createCategory(request, image);
        CategoryResponse response = this.categoryMapper.toCategoryResponse(category);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "category",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> updateCategory(@RequestParam long id,
                                                           @RequestPart("body") CategoryRequest request,
                                                           @RequestPart(required = false) MultipartFile image) {
        Category category = this.categoryService.updateCategory(id, request, image);
        CategoryResponse response = this.categoryMapper.toCategoryResponse(category);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("category")
    public void deleteCategory(@RequestParam long id) {
        this.categoryService.deleteCategory(id);
    }
}
