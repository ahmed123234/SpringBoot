package com.example.carrentalservice.AppUser;

import com.example.carrentalservice.registerationprocess.RegistrationRequest;
import com.example.carrentalservice.registerationprocess.RegistrationValidation;
import com.example.carrentalservice.token.ConfirmationToken;
import com.example.carrentalservice.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final RegistrationValidation registrationValidation;

    private final static String USER_NOT_FOUND_MESSAGE=
            "User with email %s not found!";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    public void checkEmail(String email) {

        boolean emailExists = appUserRepository
                .findByEmail(email)
                .isPresent();

        if (emailExists) {

            throw new IllegalStateException("email already taken");
        }
    }

    public void checkUsername(String username) {

        boolean userNameExists = appUserRepository
                .findByUsername(username)
                .isPresent();

        if (userNameExists) {

            throw new IllegalStateException("userName already taken");
        }

    }

    public String signUpUser(AppUser appUser){

        checkEmail(appUser.getEmail());

        checkUsername(appUser.getUsername());

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        //UserDetails userDetails = User.builder().roles(appUser.getAppUserRole().name()).build();

//        setUserRole(appUser, role);

        appUserRepository.save(appUser);

        // Generate a random token
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        return token;

    }


    public void enableAppUser(String email) {

        appUserRepository.enableAppUser(email);
    }


    public Optional<List<AppUser>> getByUserRole(AppUserRole appUserRole) {
        boolean isFound = appUserRepository.findByAppUserRole(appUserRole).isPresent();

        if(!isFound){
            throw new IllegalStateException("No such users with the role " + appUserRole);
        }
        return appUserRepository.findByAppUserRole(appUserRole);
    }

    public String getUser(String username, String password) {
        final String USER_NOT_FOUND_MESSAGE = "No such user with the given credentials";

        boolean isFound = appUserRepository.findByUsernameAndPassword(username, password).isPresent();

        if(isFound)

            return "The user with username (" + username + ") and password (" + password + ") is valid";

        throw  new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, username));

    }
    @Transactional
    public void CreateNewUserAfterOAuthLoginSuccess(String email, String firstName, String lastName, AuthenticationProvider provider) {

        AppUser appUser = new AppUser();
        appUser.setEmail(email);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setEnabled(true);
        appUser.setAuthenticationProvider(provider);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setUsername(lastName + firstName);

        appUserRepository.save(appUser);
    }

    public void updateUserInfo(String email, AuthenticationProvider provider) {

//        Optional<AppUser> appUser =appUserRepository.findByEmail(email);
//
//        appUser.get().setAuthenticationProvider(provider);
        appUserRepository.updateAppUserInfo(email, provider);
    }

    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    public String  changeStatus(Long userId, boolean status) {
        String statusDescription;
        appUserRepository.UpdateStatus(userId, status);
        if(status) {
            statusDescription = "Enabled";
        }
        else statusDescription = "Disabled";
        return "The user with Id " + userId + "is successfully " + statusDescription;
    }

    public String deleteUser (Long userId) {
        appUserRepository.deleteUser(userId);

        return "The user with Id " + userId + "is successfully deleted";
    }

    public void addUser(RegistrationRequest registrationRequest) {
        registrationValidation.validateUserInfo(registrationRequest.getEmail(),
                registrationRequest.getUserName(),
                registrationRequest.getPassword()
                );
        checkEmail(registrationRequest.getEmail());
        checkUsername(registrationRequest.getUserName());
        AppUser appUser = new AppUser(
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                registrationRequest.getUserName(),
                registrationRequest.getPassword(),
                registrationRequest.getRole()
        );

        //setUserRole(appUser, registrationRequest.getRole());

        appUser.setEnabled(true);
        appUserRepository.save(appUser);
    }

    public Long getUserId (String username) {
        return appUserRepository.findByUsername(username).get().getUserId();
    }

    public String updateUserPassword(Principal principal, String password) {

       // String message;
        UserDetails loginUser = (UserDetails) ((Authentication) principal).getPrincipal();
       // message = "Hi " + loginUser.getUsername() + ". Your password changed from " + loginUser.getPassword();
        appUserRepository.updateUserPassword(loginUser.getUsername(), password);
       // message += " to " + loginUser.getPassword() + "successfully";

        return "Password updated successfully";

    }
}