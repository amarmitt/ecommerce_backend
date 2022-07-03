package com.vegitable.store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class RoleDto {
    long roleId;
    String roleName;
//    Set<User> user;

}
