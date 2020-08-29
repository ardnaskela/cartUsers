package com.cartdemo.user.controller;

import com.cartdemo.user.dto.AddressDto;
import com.cartdemo.user.dto.ResponseDto;
import com.cartdemo.user.dto.UserDto;
import com.cartdemo.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static com.cartdemo.user.Constants.*;

@RestController
@Log4j2
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @GetMapping(produces="application/json")
    public @ResponseBody
    ResponseEntity<ResponseDto> getAllUsers() {
        List<UserDto> ud = userService.getAllUsers();
        return new ResponseEntity<>(new ResponseDto(OK, DATA_FOUND, LocalDateTime.now(), ud), HttpStatus.OK);
    }

    @PostMapping(produces="application/json")
    public @ResponseBody
    ResponseEntity<ResponseDto> saveUser(@RequestBody @Valid UserDto userDto) {
        userService.save(userDto);
        return new ResponseEntity<>(new ResponseDto(OK, USER_SAVED, LocalDateTime.now(), null), HttpStatus.OK);
    }

    @GetMapping(value="/{email}", produces="application/json")
    public @ResponseBody
    ResponseEntity<ResponseDto> getUserByEmail(@PathVariable("email")  String email, @RequestParam(required = false) Boolean active) {
        UserDto ud;
        if (active == null)
            ud = userService.findUserByEmail(email);
        else
            ud = userService.findUserByEmailAndState(email, active);
        return new ResponseEntity<>(new ResponseDto(OK, USER_FOUND, LocalDateTime.now(), ud), HttpStatus.OK);
    }

    @PostMapping(value="/address", produces="application/json")
    public @ResponseBody
    ResponseEntity<ResponseDto> updateUserWithAddress(@RequestParam Long userId, @RequestBody @Valid AddressDto addressDto) {
        userService.updateUserWithAddress(userId, addressDto);
        return new ResponseEntity<>(new ResponseDto(OK, USER_SAVED, LocalDateTime.now(), null), HttpStatus.OK);
    }
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping(produces="application/json")
    public @ResponseBody
    ResponseEntity<ResponseDto> deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ResponseDto(OK, USER_DELETED, LocalDateTime.now(), null), HttpStatus.OK);
    }
}
