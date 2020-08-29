package com.cartdemo.user.exception;

import static com.cartdemo.user.Constants.NO_USER;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7729602272924556030L;

    public UserNotFoundException(String email) {
        super(String.format("%s%s%s", NO_USER, ": ", email));
    }
}
