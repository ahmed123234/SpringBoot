package com.example.loginregisterationbackend.registration;

import com.example.loginregisterationbackend.appuser.AppUser;
import com.example.loginregisterationbackend.appuser.AppUserRole;
import com.example.loginregisterationbackend.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service // to register a new user
@AllArgsConstructor
public class RegistrationService {
    private  final EmailValidator emailValidator;
    private final AppUserService appUserService;
    public String register(RegistrationRequest registrationRequest) {
        // first we want to check if the username is valid or not
        boolean isValidEmail = emailValidator.test(registrationRequest.getUsername());

        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(new AppUser(
            registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getUsername(),
                registrationRequest.getPassword(),
                AppUserRole.USER
        ));
    }
}
