package com.cartdemo.user.controller;

import com.cartdemo.user.TestData;
import com.cartdemo.user.dto.UserDto;
import com.cartdemo.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.cartdemo.user.Constants.OK;
import static com.cartdemo.user.Constants.USER_SAVED;
import static com.cartdemo.user.TestData.USER_DTO_LST;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsers() throws Exception {

        when(userService.getAllUsers()).thenReturn(TestData.getUserDtoTestListWithId());

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.body", hasSize(TestData.getUserDtoTestListWithId().size())))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void saveUser(){
        UserDto userToSave = USER_DTO_LST.get(1);
        when(userService.save(Mockito.any(UserDto.class))).thenReturn(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String userSavedJSON = objectMapper.writeValueAsString(userToSave);

        ResultActions resultActions = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSavedJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(OK))
                .andExpect(jsonPath("$.message").value(USER_SAVED));
    }

    @Test
    void getUserByEmail() {
    }

}
