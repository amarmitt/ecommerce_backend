package com.vegitable.store.services.Impl;

import com.vegitable.store.dto.SignInDto;
import com.vegitable.store.dto.UserDto;
import com.vegitable.store.entities.User;
import com.vegitable.store.exception.CustomException;
import com.vegitable.store.mapper.MapStructMapper;
import com.vegitable.store.repositories.UserRepository;
import com.vegitable.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MapStructMapper mapStructMapper;


    @Override
    public User checkIfUserAleadyExist(String emailId) {
        return userRepository.findUserByEmailId(emailId);
    }

    @Override
    public User createUser(UserDto userDto) {
//        if (checkIfUserAleadyExist(userDto.getEmailId()) != null) {
//            SQLException SQLException = new SQLException();
//            throw new ConstraintViolationException(" Unique Constraint Violation Exception: User is already exist with given emailId",
//                    SQLException, userDto.getEmailId());
//        }

//        Role role = Role.builder()
//                .roleName("User")
//                .build();
//        User user = User.builder()
//                .firstName(userDto.getFirstName())
//                .lastName(userDto.getLastName())
//                .emailId(userDto.getEmailId())
//                .password(passwordEncoder.encode(userDto.getPassword()))
//                .role(Set.of(role))
//                .build();

        User user = mapStructMapper.userDtotoUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Boolean match = passwordEncoder.matches("root1", user.getPassword());
        System.out.println(match);

        User userResponse = userRepository.save(user);


        return userResponse;
    }

    @Override
    public UserDto updateUser(UserDto userDto, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("User Id is Not found"));

        return mapStructMapper.usertoUserDto(user);

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        System.out.println("USer " + users);
        List<UserDto> userDtoList = users.stream()
                .map(user -> mapStructMapper.usertoUserDto(user))
                .collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public UserDto signIn(SignInDto signInDto) {

        User user = userRepository.findUserByEmailId(signInDto.getEmailId());
        if (user == null) {
            throw new CustomException("Email is not correct");
        }
        boolean passwordMatch = passwordEncoder.matches(signInDto.getPassword(), user.getPassword());
        if (!passwordMatch) {
            throw new CustomException("Password is not correct");
        }
        UserDto userDto = mapStructMapper.usertoUserDto(user);
        userDto.setPassword(signInDto.getPassword());
        return userDto;
    }

    @Override
    public void deleteUserId(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("User Id is Not found"));
        this.userRepository.delete(user);
    }
}
