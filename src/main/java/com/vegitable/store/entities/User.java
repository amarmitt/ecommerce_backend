package com.vegitable.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User_Info",
        uniqueConstraints = @UniqueConstraint(name = "emailId_unique", columnNames = "email_address")
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ToString
//@EqualsAndHashCode(callSuper = false)
public class User {
    @Id
    @Column(name = "user_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userId;

    @Column(nullable = false, length = 50)
    String firstName;

    @Column(nullable = false, length = 50)
    String lastName;

    @NonNull
    @Column(name = "email_address" /**, unique = true*/)
    String emailId;

    String password;


    @Column(name = "phone_number")
    String phone;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role_Info",
            joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "user_Id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_Id")
    )
    Set<Role> role;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_address_fk", referencedColumnName = "user_Id")
    List<Address> addressList;

}
