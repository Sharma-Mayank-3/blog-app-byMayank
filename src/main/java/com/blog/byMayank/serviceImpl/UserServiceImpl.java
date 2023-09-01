package com.blog.byMayank.serviceImpl;

import com.blog.byMayank.dto.UserDto;
import com.blog.byMayank.entity.User;
import com.blog.byMayank.exception.ResourceNotFoundException;
import com.blog.byMayank.repository.UserRepo;
import com.blog.byMayank.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        User savedUser = this.userRepo.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> all = this.userRepo.findAll();
        List<UserDto> collect = all.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String deleteUser(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        this.userRepo.delete(user);
        return "user deleted";
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        User map = this.modelMapper.map(userDto, User.class);
        map.setUserId(user.getUserId());
        User save = this.userRepo.save(map);
        return this.modelMapper.map(save, UserDto.class);
    }
}
