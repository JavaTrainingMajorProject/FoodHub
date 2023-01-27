package com.Foodservice.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Data
public class FoodMenuDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Invalid   Name: Empty first name")
    @NotNull(message = "Invalid  Name:  name is NULL")
    @Size(min = 3, max = 15, message = "Invalid  Name: Exceeds 15 characters")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Invalid Price Name: Price  is NULL")
    private Double price;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_available", nullable = false)
    @NotNull(message = "Invalid isAvailable: isAvailable  is NULL")
    private boolean isAvailable;

    @Column(name = "category")
    @NotNull(message = "Invalid Category Type: category  is NULL")
    private String category;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "review_count")
    private Long reviewCount;

    public FoodMenuDto() {
    }


    public FoodMenuDto(String name, String description, Double price,
                    String ingredients, String imageUrl,
                    boolean isAvailable, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
        this.isAvailable = isAvailable;
        this.category = category;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }
}
