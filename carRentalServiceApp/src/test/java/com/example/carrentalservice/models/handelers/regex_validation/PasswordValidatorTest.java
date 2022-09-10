package com.example.carrentalservice.models.handelers.regex_validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    private PasswordValidator passwordValidator;
    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    void testIfPasswordIsValid() {
        //given
        String password = "201712@Qas";

        //when
        boolean actual = passwordValidator.test(password);

        //then
        assertThat(actual).isTrue();
    }
}