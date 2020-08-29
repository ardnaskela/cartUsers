package com.cartdemo.user.controller;

import com.cartdemo.user.exception.UserNotFoundException;
import com.cartdemo.user.service.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.cartdemo.user.Constants.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ControllerAdvisorTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @SneakyThrows
    @Test
    void handleUserNotFoundException() {
        String email="mail@mail.com";
        when(userService.findUserByEmail(Mockito.any(String.class))).thenThrow(new UserNotFoundException(String.format("email = %s", email)));

        ResultActions resultActions = mockMvc.perform(get("/users/" + email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(KO))
                .andExpect(jsonPath("$.message").value(NO_DATA));
    }

    @Test
    void handleDataIntegrityViolationException() {
    }

    @Test
    void handleMethodArgumentNotValid() {
    }

    @Test
    void handleNoHandlerFoundException() {
    }
}