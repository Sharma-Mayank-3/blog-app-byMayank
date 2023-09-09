package com.blog.byMayank.controller;

import com.blog.byMayank.dto.UserDto;
import com.blog.byMayank.service.UserService;
import com.blog.byMayank.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog-app/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDto userDto){
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(new ApiResponse("new user is created", true, "user-service", user), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllUsers(){
        List<UserDto> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(new ApiResponse("all users", true, "user-service", allUsers), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable("userId") int userId){
        UserDto userById = this.userService.getUserById(userId);
        return new ResponseEntity<>(new ApiResponse("user By Id", true, "user-service", userById), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") int userId){
        UserDto userDto1 = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<>(new ApiResponse("update user", true, "user-service", userDto1), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") int userId){
        String s = this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("user deleted", true, "user-service", s), HttpStatus.OK);
    }

}
