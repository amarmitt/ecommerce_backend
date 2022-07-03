package com.vegitable.store.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category_fk", referencedColumnName = "category_id")
    Category category;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_Id")
    private Long productId;
    @NotNull
    private String name;
    @NotNull
    private String imageURL;
    @NotNull
    private double price;
    @NotNull
    private String description;
}