package com.example.carrentalservice.models.handelers.regex_validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmailValidatorTest {
    EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    @Test
    void canTestIfEmailIsValid() {
        //given
        String email = "ahmad@gmail.com";

        //when
        boolean actual = emailValidator.test(email);

        //then
        assertThat(actual).isTrue();
    }
}