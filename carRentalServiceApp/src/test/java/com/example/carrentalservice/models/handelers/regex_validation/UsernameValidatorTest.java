package com.example.carrentalservice.models.handelers.regex_validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UsernameValidatorTest {

    private UsernameValidator usernameValidator;

    @BeforeEach
    void setUp() {
        usernameValidator =new UsernameValidator();
    }

    @Test
    void testUsername() {
        //given
        String username = "5ahmad";

        //when
        boolean actual = usernameValidator.test(username);

        //then
        assertThat(actual).isFalse();
    }
}