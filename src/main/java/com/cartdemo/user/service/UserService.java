package com.cartdemo.user.service;

import com.cartdemo.user.dto.AddressDto;
import com.cartdemo.user.dto.UserDto;
import com.cartdemo.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getAllUsers();
    Long save(UserDto userDto);
    UserDto findUserByEmail(String email);
    UserDto findUserByEmailAndState(String email, boolean active);
    UserDto findUserById(Long id);
    UserEntity updateUserWithAddress(Long id, AddressDto addressDto);
    void deleteUser(Long id);
}
