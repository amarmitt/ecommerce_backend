package com.vegitable.store.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "Order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_tracking_number")
    UUID orderTrackingNumber;

    long Quantity;

    double total;

    @Column(name = "status")
    String status;

    @Column(name = "date_created")
    @CreationTimestamp
    Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    Date lastUpdated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order", orphanRemoval = true)
    Set<OrderItem> orderItems;

}
