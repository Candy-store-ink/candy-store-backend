package com.github.candy.store.modules.product;

import com.github.candy.store.modules.category.Category;
import com.github.candy.store.modules.product.payload.ProductRequest;
import com.github.candy.store.modules.user.User;
import com.github.candy.store.utils.image.ImageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FavoriteProductsRepository favoriteProductsRepository;
    private final ImageHandler imageHandler;

    @Override
    public Page<Product> getProducts(Long categoryId, String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Product> getVisibleProducts(Long categoryId, String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllByCategoryIdAndVisibleTrue(categoryId, pageable);
    }

    @Override
    public boolean isFavorite(Product product, User user) {
        return favoriteProductsRepository.existsByProductAndUser(product, user);
    }

    @Override
    public Product createProduct(ProductRequest request, Category category, MultipartFile image) {
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(new BigDecimal(request.price()));
        product.setDescription(request.description());
        String link = imageHandler.uploadImage(image);
        product.setImage(link);
        product.setCategory(category);
        return productRepository.save(product);
    }
}
