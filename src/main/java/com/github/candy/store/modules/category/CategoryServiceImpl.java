package com.github.candy.store.modules.category;

import com.github.candy.store.exception.CategoryNotFoundException;
import com.github.candy.store.modules.category.payload.CategoryRequest;
import com.github.candy.store.utils.image.ImageService;
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
    private final ImageService imageService;

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
        String link = imageService.uploadImage(image);
        category.setImage(link);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategory(long id, CategoryRequest request, MultipartFile image) {
        Category category = getCategory(id);
        category.setName(request.name());
        if (image != null) {
            String link = imageService.uploadImage(image);
            category.setImage(link);
        }
        return categoryRepository.save(category);
    }
}
