package com.example.carrentalservice.models.handelers.regex_validation;

import com.example.carrentalservice.exception.ApiRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RegistrationValidationTest {
    private RegistrationValidation registrationValidation;

    @Mock
    UsernameValidator usernameValidator;
    @Mock
    PasswordValidator passwordValidator;
    @Mock
    EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        registrationValidation = new RegistrationValidation(
                emailValidator,
                passwordValidator,
                usernameValidator
        );
    }

    @Test
    void validateUserInfoIsEmailValid() {
        //given
        String email = "ahmad@gamil.com";

     Mockito.when(emailValidator.test(email)).thenReturn(true);
        boolean actual = emailValidator.test(email);
        assertThat(actual).isTrue();
    }

    @Disabled
    @Test
    void validateUserInfoIsEmailNotValid() {
        //given
        String email = "ahmad.com";

        given(emailValidator.test(email)).willReturn(false);
        assertThatThrownBy(()->registrationValidation.validateUserInfo(email,null,""))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("The entered email is not valid");

    }

    @Test
    void validateUserInfoIsPasswordValid() {
        //given
        String password = "20171@Asg";

        Mockito.when(passwordValidator.test(password)).thenReturn(true);
        boolean actual = passwordValidator.test(password);
        assertThat(actual).isTrue();
    }

    @Test
    void validateUserInfoIsPasswordNotValid() {
        //given
        String password = "asm899";

        given(passwordValidator.test(password)).willReturn(false);
        assertThatThrownBy(()->registrationValidation.validateUserInfo(null,null,password))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("The entered password is not valid");

    }

    @Test
    void validateUserInfoIsUsernameValid() {
        //given
        String username = "ahmad12";

        Mockito.when(usernameValidator.test(username)).thenReturn(true);
        boolean actual = usernameValidator.test(username);
        assertThat(actual).isTrue();
    }

    @Test
    void validateUserInfoIsUsernameNotValid() {
        //given
        String username = "asm899";

        given(usernameValidator.test(username)).willReturn(false);
        assertThatThrownBy(()->registrationValidation.validateUserInfo(null,username,""))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("The entered username is not valid");

    }
}