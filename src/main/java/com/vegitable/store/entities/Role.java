package com.vegitable.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ToString
//@EqualsAndHashCode(callSuper = false)
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long roleId;
    @Column(name = "role_name", nullable = false)
    String roleName;
//    @Transient
//    @ManyToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    Set<User> user;

}
