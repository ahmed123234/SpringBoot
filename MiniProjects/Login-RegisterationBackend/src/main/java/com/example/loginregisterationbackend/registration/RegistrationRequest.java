package com.example.loginregisterationbackend.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter // this annotation will capture the Getter methods
@AllArgsConstructor // this annotation will capture the Constructors that can be overloaded
@EqualsAndHashCode
@ToString // this annotation will capture the ToString method
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String username;
}
