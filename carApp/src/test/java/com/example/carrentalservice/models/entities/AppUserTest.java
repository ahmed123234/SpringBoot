package com.example.carrentalservice.models.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {AppUser.class})
@ExtendWith(SpringExtension.class)
class AppUserTest {

    @Test
    void getAuthorities() {
    }

    @Test
    void getPassword() {
    }

    @Test
    void getUsername() {
    }

    @Test
    void isAccountNonExpired() {
    }

    @Test
    void isAccountNonLocked() {
    }

    @Test
    void isCredentialsNonExpired() {
    }

    @Test
    void isEnabled() {
    }

    @Test
    void addRole() {
    }

    @Test
    void getUserId() {
    }

    @Test
    void getFirstName() {
    }

    @Test
    void getLastName() {
    }

    @Test
    void getEmail() {
    }

    @Test
    void getRoles() {
    }

    @Test
    void getAuthenticationProvider() {
    }

    @Test
    void getLocked() {
    }

    @Test
    void getEnabled() {
    }

    @Test
    void setUserId() {
    }

    @Test
    void setFirstName() {
    }

    @Test
    void setLastName() {
    }

    @Test
    void setEmail() {
    }

    @Test
    void setUsername() {
    }

    @Test
    void setPassword() {
    }

    @Test
    void setRoles() {
    }

    @Test
    void setAuthenticationProvider() {
    }

    @Test
    void setLocked() {
    }

    @Test
    void setEnabled() {
    }
}