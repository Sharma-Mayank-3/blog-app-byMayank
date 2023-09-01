package com.blog.byMayank.service;

import com.blog.byMayank.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(int userId);

    List<UserDto> getAllUsers();

    String deleteUser(int userId);

    UserDto updateUser(UserDto userDto, int userId);

}
