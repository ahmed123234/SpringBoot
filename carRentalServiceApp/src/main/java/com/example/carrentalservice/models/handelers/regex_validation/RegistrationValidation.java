package com.example.carrentalservice.models.handelers.regex_validation;

import com.example.carrentalservice.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
@AllArgsConstructor
public class RegistrationValidation {
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final UsernameValidator usernameValidator;

    public void validateUserInfo(@Nullable String email, @Nullable String username, String password) {
        // first we want to check if email, userName and password is valid or not
        boolean isValidEmail = emailValidator.test(email);
        boolean isValidPassword = passwordValidator.test(password);
        boolean isValidUseName = usernameValidator.test(username);

        if (!isValidEmail) {
            throw new ApiRequestException("The entered email is not valid");
        }

        if (!isValidPassword) {
            throw new ApiRequestException("The entered password is not valid");
        }

        if (!isValidUseName) {
            throw new ApiRequestException("The entered username is not valid");
        }

    }
}
