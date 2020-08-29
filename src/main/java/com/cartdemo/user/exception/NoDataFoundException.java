package com.cartdemo.user.exception;

import static com.cartdemo.user.Constants.NO_DATA;

public class NoDataFoundException extends RuntimeException {
    private static final long serialVersionUID = 5348543648810991807L;

    public NoDataFoundException() {
        super(NO_DATA);
    }
}
