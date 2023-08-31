package com.github.candy.store.modules.product;

import com.github.candy.store.exception.ProductNotFoundException;
import com.github.candy.store.modules.category.Category;
import com.github.candy.store.modules.product.payload.ProductRequest;
import com.github.candy.store.modules.user.User;
import com.github.candy.store.utils.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FavoriteProductsRepository favoriteProductsRepository;
    private final ImageService imageService;

    @Override
    public Product getById(long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getProducts(Long categoryId, String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (categoryId == null) {
            return productRepository.findAll(pageable);
        }
        return productRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Product> getVisibleProducts(Long categoryId, String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (categoryId == null) {
            return productRepository.findAllByVisibleTrue(pageable);
        }
        return productRepository.findAllByCategoryIdAndVisibleTrue(categoryId, pageable);
    }

    @Override
    public boolean isFavorite(Product product, User user) {
        return favoriteProductsRepository.existsByProductAndUser(product, user);
    }

    @Override
    public Product createProduct(ProductRequest request, Category category, MultipartFile image) {
        Product product = new Product();
        fillProduct(request, category, image, product);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long id, ProductRequest request, Category category, MultipartFile image) {
        Product product = getById(id);
        fillProduct(request, category, image, product);
        return productRepository.save(product);
    }

    private void fillProduct(ProductRequest request, Category category, MultipartFile image, Product product) {
        product.setName(request.name());
        product.setPrice(new BigDecimal(request.price()));
        product.setDescription(request.description());
        product.setManufacturer(request.manufacturer());
        product.setVisible(request.isVisible());
        if (request.expiredDate() != null) {
            product.setExpirationDate(new Date(request.expiredDate()));
        }
        product.setCategory(category);
        if (image != null) {
            String link = imageService.uploadImage(image);
            product.setImage(link);
        }
    }
}
