package com.github.candy.store.modules.product;

import com.github.candy.store.modules.category.Category;
import com.github.candy.store.modules.orderProducts.OrderProducts;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price", columnDefinition = "DECIMAL(10,2)")
    private BigDecimal price;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "is_visible")
    private boolean visible;

    @Column(name = "is_removed")
    private boolean removed;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<FavoriteProducts> favoriteProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderProducts> orderProducts = new ArrayList<>();

}