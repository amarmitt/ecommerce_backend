package com.vegitable.store.dto;

import com.vegitable.store.entities.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class ProductDto {
    Category category;
    Long productId;
    @NotNull
    String name;
    @NotNull
    String imageURL;
    @NotNull
    double price;
    @NotNull
    String description;
}
