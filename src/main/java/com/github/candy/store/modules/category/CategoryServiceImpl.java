package com.github.candy.store.modules.category;

import com.github.candy.store.exception.CategoryNotFoundException;
import com.github.candy.store.modules.category.payload.CategoryRequest;
import com.github.candy.store.utils.image.ImageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ImageHandler imageHandler;

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Page<Category> getCategories(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category createCategory(CategoryRequest request, MultipartFile image) {
        Category category = new Category();
        category.setName(request.name());
        String link = imageHandler.uploadImage(image);
        category.setImage(link);
        return categoryRepository.save(category);
    }
}
