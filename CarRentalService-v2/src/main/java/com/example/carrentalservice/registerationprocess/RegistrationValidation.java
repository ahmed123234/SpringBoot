package com.example.carrentalservice.registerationprocess;

import com.example.carrentalservice.registerationprocess.regex_validation.EmailValidator;
import com.example.carrentalservice.registerationprocess.regex_validation.PasswordValidator;
import com.example.carrentalservice.registerationprocess.regex_validation.UsernameValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegistrationValidation {
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final UsernameValidator usernameValidator;

    public void validateUserInfo(String email, String username, String password) {
        // first we want to check if email, userName and password is valid or not
        boolean isValidEmail = emailValidator.test(email);
        boolean isValidPassword = passwordValidator.test(password);
        boolean isValidUseName = usernameValidator.test(username);

        if (!isValidEmail) {
            throw new IllegalStateException("The entered email is not valid");
        }

        if (!isValidPassword) {
            throw new IllegalStateException("The entered password is not valid");
        }

        if (!isValidUseName) {
            throw new IllegalStateException("The entered userName is not valid");
        }

    }
}
