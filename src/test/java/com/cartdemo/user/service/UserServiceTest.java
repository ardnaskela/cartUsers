package com.cartdemo.user.service;

import com.cartdemo.user.dto.UserDto;
import com.cartdemo.user.entity.UserEntity;
import com.cartdemo.user.exception.UserNotFoundException;
import com.cartdemo.user.repository.UserRepository;
import com.cartdemo.user.util.FieldsComparator;
import com.cartdemo.user.util.Mappers;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static com.cartdemo.user.TestData.*;
import static com.cartdemo.user.util.FieldsComparator.fieldsAreEqual;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Log4j2
public class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    Mappers mappers;

    @SneakyThrows
    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(getUserEntityTestListWithId());
        UserService userService = new UserServiceImpl(userRepository, mappers);
        List<UserDto> dtoList = userService.getAllUsers();

        assertTrue(FieldsComparator.fieldsAreEqualForLists(getUserEntityTestListWithId(), dtoList, new String[]{"addresses"}));
        }

    @SneakyThrows
    @Test
    void saveUser() {
        UserDto userDto = getUserDtoTestListWithId().get(2);
        when(userRepository.save(any(UserEntity.class))).thenReturn(mappers.userDtoToUserEntity(userDto));
        UserService userService = new UserServiceImpl(userRepository, mappers);
        Long savedUserId = userService.save(userDto);

        assertEquals(userDto.getId(), savedUserId);
        }

    @Test
    void findUserByEmail() {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(getUserEntityTestListWithId().get(0)));
        UserService userService = new UserServiceImpl(userRepository, mappers);
        UserDto dto = userService.findUserByEmail("mail@mail.com");
        assertTrue(fieldsAreEqual(getUserEntityTestListWithId().get(0), dto, new String[] {"addresses"}));

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {userService.findUserByEmail("mail@mail.com"); });
        }

    @Test
    void findActiveUserById() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(getUserEntityTestListWithId().get(0)));
        UserService userService = new UserServiceImpl(userRepository, mappers);
        UserDto dto = userService.findUserById(1L);
        assertTrue(fieldsAreEqual(getUserEntityTestListWithId().get(0), dto, new String[] {"addresses"}));

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {userService.findUserById(1L); });
    }

    @Test
    public void updateUserWithAddress() {
        UserEntity testUser = getUserEntityTestListWithId().get(0);
        UserEntity testUserWithAddress = getUserEntityTestListWithId().get(0);
        testUserWithAddress.getAddresses().add(getAddressEntityTestListWithId().get(0));

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(testUserWithAddress);

        UserService userService = new UserServiceImpl(userRepository, mappers);

        UserEntity ue = userService.updateUserWithAddress(1L, ADDRESS_DTO_LST.get(0));

        assertTrue(fieldsAreEqual(testUserWithAddress, ue));
    }


    }

