package com.cartdemo.user.service;

import com.cartdemo.user.dto.AddressDto;
import com.cartdemo.user.dto.UserDto;
import com.cartdemo.user.entity.UserEntity;
import com.cartdemo.user.exception.NoDataFoundException;
import com.cartdemo.user.exception.UserNotFoundException;
import com.cartdemo.user.repository.UserRepository;
import com.cartdemo.user.util.Mappers;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.cartdemo.user.Constants.USER_SAVED;

@Service
@Log4j2
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final Mappers mappers;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        if (userEntities.isEmpty())
            throw new NoDataFoundException();
        for (UserEntity user: userRepository.findAll())
            users.add(mappers.userEntityToUserDto(user));
        return users;
    }

    @Override
    public Long save(UserDto userDto) {
        UserEntity ue = userRepository.save(mappers.userDtoToUserEntity(userDto));
        log.debug(String.format("%s: %s", USER_SAVED, ue.toString()));
        return ue.getId();
    }

    @Override
    public UserDto findUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(String.format("email = %s", email)));
        return mappers.userEntityToUserDto(user);
    }

    @Override
    public UserDto findUserByEmailAndState(String email, boolean active) {
        UserEntity user = userRepository.findByEmailAndActiveIs(email, active).orElseThrow(() -> new UserNotFoundException(String.format("email = %s, active = %s", email, String.valueOf(active))));
        return mappers.userEntityToUserDto(user);
    }

    @Override
    public UserDto findUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("id = %d, active = true", id)));
        return mappers.userEntityToUserDto(user);
    }

    @Override
    public UserEntity updateUserWithAddress(Long userId, AddressDto addressDto) {
        UserDto userDto = findUserById(userId);

        // we remove the existent entry associated to UserDto so add the updated entry with the same ID.
        // It will then be updated and not inserted by UserRepository using the ID field.
        if(addressDto.getId() != null)
            userDto.getAddresses().removeIf(x -> x.getId().equals(addressDto.getId()));

        userDto.getAddresses().add(addressDto);

        UserEntity ue = userRepository.save(mappers.userDtoToUserEntity(userDto));
        log.debug(String.format("%s: %s", USER_SAVED, ue.toString()));
        return ue;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserServiceImpl(UserRepository userRepository, Mappers mappers) {
        this.userRepository = userRepository;
        this.mappers = mappers;
    }
}
