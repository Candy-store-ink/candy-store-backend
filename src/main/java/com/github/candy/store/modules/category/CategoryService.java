package com.github.candy.store.modules.category;

import com.github.candy.store.modules.category.payload.CategoryRequest;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface CategoryService {
    Category getCategory(Long categoryId);

    Page<Category> getCategories(@Nullable String search, int page, int size);

    Category createCategory(CategoryRequest request, @Nullable MultipartFile image);
}
