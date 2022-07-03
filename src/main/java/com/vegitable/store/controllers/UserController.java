package com.vegitable.store.controllers;

import com.vegitable.store.dto.SignInDto;
import com.vegitable.store.dto.UserDto;
import com.vegitable.store.entities.User;
import com.vegitable.store.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public String Home() {
        return "Hello User";
    }

   // @CrossOrigin
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {

        return new ResponseEntity<>(userServiceImpl.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
                                              @PathVariable("userId") long userId) {

        return new ResponseEntity<>(userServiceImpl.updateUser(userDto, userId), HttpStatus.CREATED);
    }

    @GetMapping("/allUser")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return new ResponseEntity<>(userServiceImpl.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserDto> getUserByEmailIdAndPassword(@RequestBody SignInDto signInDto) {

        return new ResponseEntity<>(userServiceImpl.signIn(signInDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUserbyUserId(@PathVariable("userId") long userId) {
        userServiceImpl.deleteUserId(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
