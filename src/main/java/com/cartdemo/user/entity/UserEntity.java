package com.cartdemo.user.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

import static com.cartdemo.user.Constants.PHONE_PATTERN;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@DynamicUpdate
public class UserEntity extends BaseEntity {
    @NonNull
    @Column(unique=true)
    @Email
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private LocalDate dateOfBirth;
    @NonNull
    @Pattern(regexp = PHONE_PATTERN)
    private String telephone;
    @NonNull
    private Boolean active;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userEntity")
    private List<AddressEntity> addresses;

    @Override
    public String toString() {
        return "UserEntity{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", telephone='" + telephone + '\'' +
                ", active=" + active +
                ", addresses=" + addresses +
                ", id=" + id +
                '}';
    }
}
