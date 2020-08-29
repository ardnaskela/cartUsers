package com.cartdemo.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.cartdemo.user.Constants.*;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -5230854997140399568L;
    @NotEmpty(message = EMAIL + IS_EMPTY)
    @Email(message = EMAIL + IS_INVALID)
    private String email;
    @NotEmpty(message = PASSWORD + IS_EMPTY)
    private String password;
    @NotEmpty(message = NAME + IS_EMPTY)
    private String firstName;
    @NotEmpty(message = SURNAME + IS_EMPTY)
    private String lastName;
    @NotEmpty(message = DOB + IS_EMPTY)
    @Pattern(regexp = DATE_PATTERN, message = DOB + IS_INVALID)
    private String dateOfBirth;
    @NotEmpty(message = PHONE + IS_EMPTY)
    @Pattern(regexp = PHONE_PATTERN, message=PHONE + IS_INVALID)
    private String telephone;
    @NotNull(message = STATUS + IS_EMPTY)
    private Boolean active;
    private List<AddressDto> addresses=new ArrayList<>();

    public UserDto(@NotEmpty(message = EMAIL + IS_EMPTY) @Email(message = EMAIL + IS_INVALID) String email, @NotEmpty(message = PASSWORD + IS_EMPTY) String password, @NotEmpty(message = NAME + IS_EMPTY) String firstName, @NotEmpty(message = SURNAME + IS_EMPTY) String lastName, @NotEmpty(message = DOB + IS_EMPTY) @Pattern(regexp = DATE_PATTERN, message = DOB + IS_INVALID) String dateOfBirth, @NotEmpty(message = PHONE + IS_EMPTY) @Pattern(regexp = PHONE_PATTERN, message = PHONE + IS_INVALID) String telephone, @NotNull(message = STATUS + IS_EMPTY) Boolean active) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.telephone = telephone;
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", telephone='" + telephone + '\'' +
                ", active=" + active +
                '}';
    }
}
