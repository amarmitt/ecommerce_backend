package com.vegitable.store.mapper;

import com.vegitable.store.dto.ProductDto;
import com.vegitable.store.dto.UserDto;
import com.vegitable.store.entities.Product;
import com.vegitable.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserDto usertoUserDto(User user);

    User userDtotoUser(UserDto userDto);

    ProductDto producttoProductDto(Product product);

    Product productDtotoProduct(ProductDto productDto);


}
