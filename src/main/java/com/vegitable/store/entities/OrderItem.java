package com.vegitable.store.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orderItems")
public class OrderItem {
    @Id
    @Column(name = "orderItem_Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String imageUrl;

    double price;

    long Quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_Id", insertable = true, updatable = true)
    Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", insertable = true, updatable = true)
    private Order order;
}
