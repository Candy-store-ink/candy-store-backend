package com.github.candy.store.controllers.user;

import com.github.candy.store.modules.category.Category;
import com.github.candy.store.modules.category.CategoryMapper;
import com.github.candy.store.modules.category.CategoryResponse;
import com.github.candy.store.modules.category.CategoryService;
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
public class CategoryControllerUser implements UserRequestMapping {

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


}
