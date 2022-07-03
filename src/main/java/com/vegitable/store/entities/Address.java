package com.vegitable.store.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "address_info")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id
    @Column(name = "addess_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Sequence_generator")
    @SequenceGenerator(name = "Sequence_generator",
            sequenceName = "Sequence_generator",
            allocationSize = 1)
    Long id;

    @NotBlank
    String address;
    String city;
    @NotBlank
    String country;
    @NotBlank
    String pinCode;
    @NotBlank
    String state;


}

