package com.cartdemo.user.util;

import com.cartdemo.user.dto.AddressDto;
import com.cartdemo.user.dto.UserDto;
import com.cartdemo.user.entity.AddressEntity;
import com.cartdemo.user.entity.UserEntity;
import com.cartdemo.user.enumeration.AddressType;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class Mappers {
    private final ModelMapper modelMapper;

    public Mappers(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserEntity git userDtoToUserEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setDateOfBirth(LocalDate.parse(userDto.getDateOfBirth()));

        List<AddressEntity> addressEntities = new ArrayList<>();
        for (AddressDto addressDto : userDto.getAddresses()) {
            AddressEntity addressEntity = addressDtoToAddressEntity(addressDto);
            addressEntities.add(addressEntity);
            addressEntity.setUserEntity(userEntity);
        }

        userEntity.setAddresses(addressEntities);

        log.debug("Updating user: " + userEntity.toString());
        return userEntity;
    }

    private AddressEntity addressDtoToAddressEntity(AddressDto addressDto) {
        AddressEntity addressEntity = modelMapper.map(addressDto, AddressEntity.class);
        addressEntity.setType(AddressType.valueOf(addressDto.getType()));
        return addressEntity;
    }

    public UserDto userEntityToUserDto(UserEntity userEntity) {
        List<AddressDto> addressDtos = new ArrayList<>();
        for (AddressEntity addressEntity : userEntity.getAddresses()) {
            addressDtos.add(addressEntityToAddressDto(addressEntity));
        }
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        userDto.setDateOfBirth(userEntity.getDateOfBirth().toString());
        userDto.setId(userEntity.getId());
        userDto.setAddresses(addressDtos);
        return userDto;
    }

    private AddressDto addressEntityToAddressDto(AddressEntity addressEntity) {
        AddressDto addressDto = modelMapper.map(addressEntity, AddressDto.class);
        addressDto.setType(addressEntity.getType().toString());
        return addressDto;
    }
}
