package com.vegitable.store.services;


import com.vegitable.store.dto.SignInDto;
import com.vegitable.store.dto.UserDto;
import com.vegitable.store.entities.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, long userId);

    List<UserDto> getAllUser();

    UserDto signIn(SignInDto signInDto);

    void deleteUserId(long userId);


    User checkIfUserAleadyExist(String emailId);
}
