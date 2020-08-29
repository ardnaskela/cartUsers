package com.cartdemo.user.repository;

import com.cartdemo.user.dto.UserDto;
import com.cartdemo.user.entity.UserEntity;
import com.cartdemo.user.service.UserService;
import com.cartdemo.user.util.FieldsComparator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.cartdemo.user.TestData.USER_DTO_LST;
import static com.cartdemo.user.TestData.USER_ENTITY_LST;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
@EnabledIf(value = "${dbTests.enabled}", loadContext = true)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void getAllUsers(){
        for(UserEntity ue : USER_ENTITY_LST)
            userRepository.save(ue);

        List<UserDto> users = userService.getAllUsers();
        List<UserDto> dtoLst = users.subList(users.size()-USER_ENTITY_LST.size(), users.size());

        assertTrue(FieldsComparator.fieldsAreEqualForLists(USER_ENTITY_LST, dtoLst, new String[]{"id"}));
        assertNotEquals(dtoLst.get(dtoLst.size()-1).getId(), dtoLst.get(dtoLst.size()-2).getId());
    }

    @Test
    void saveUser() {
        // creating new user
        UserDto userToCreate = USER_DTO_LST.get(0);
        int rows = userRepository.findAll().size();

        Long createdUserId = userService.save(userToCreate);
        assertEquals(userRepository.findAll().size(), rows+1);

        // updating old user
        UserDto userToUpdate = USER_DTO_LST.get(0);
        userToUpdate.setId(createdUserId);
        userToCreate.setLastName("NewLastName");

        Long updatedUserId = userService.save(userToUpdate);
        assertEquals(userRepository.findAll().size(), rows+1);
        assertEquals(userToUpdate.getId(), updatedUserId);
    }

    @Test
    void findByEmail() {
        userRepository.save(USER_ENTITY_LST.get(0));
        UserEntity ue = userRepository.findByEmail(USER_ENTITY_LST.get(0).getEmail()).get();
        assertTrue(FieldsComparator.fieldsAreEqual(USER_ENTITY_LST.get(0), ue, new String[]{"id"}));
    }

    @Test
    void findByEmailAndState() {
        UserEntity activeUser = USER_ENTITY_LST.get(0);
        activeUser.setActive(true);
        UserEntity inactiveUser = USER_ENTITY_LST.get(1);
        inactiveUser.setActive(false);
        userRepository.save(activeUser);
        userRepository.save(inactiveUser);

        assertTrue(userRepository.findByEmailAndActiveIs(activeUser.getEmail(), true).isPresent());
        assertFalse(userRepository.findByEmailAndActiveIs(inactiveUser.getEmail(), true).isPresent());
    }

    @Test
    void findByIdAndActiveTrue() {
        UserEntity activeUser = USER_ENTITY_LST.get(0);
        Long userId = userRepository.save(activeUser).getId();

        assertTrue(userRepository.findById(userId).isPresent());
    }
}
