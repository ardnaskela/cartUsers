package com.cartdemo.user;

public class Constants {
    public static final String OK = "OK";
    public static final String KO = "KO";
    public static final String USER_SAVED = "User saved";
    public static final String USER_DELETED = "User deleted";
    public static final String NOT_SAVED = "Entity could not be persisted";
    public static final String NO_DATA = "No data found";
    public static final String DATA_FOUND = "Data found";
    public static final String NO_USER = "User not found";
    public static final String USER_FOUND = "User found";
    public static final String NO_HANDLER = "No handler found";

    // REGEX
    public static final String PHONE_PATTERN = "^[0-9]{5,12}$";
    public static final String DATE_PATTERN = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";

    // VALIDATION MESSAGES
    public static final String IS_EMPTY = " is empty";
    public static final String IS_INVALID = " is not valid";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String NAME = "first name";
    public static final String SURNAME = "last name";
    public static final String DOB = "date of birth";
    public static final String PHONE = "telephone number";
    public static final String STATUS = "user status";

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}
