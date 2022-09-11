package com.example.carrentalservice.repositories;

import com.example.carrentalservice.models.entities.AppUser;
import com.example.carrentalservice.models.entities.ConfirmationToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ConfirmationTokenRepositoryTest {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown(){
        confirmationTokenRepository.deleteAll();
    }

    @Test
    void canFindByToken() {

        //given
        String token = "asd-agh-uio-567";
        AppUser user = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );


        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );


        //when
        Optional<ConfirmationToken> actual =
                confirmationTokenRepository.findByToken(token);

        //then
        actual.ifPresent(value -> assertThat(value).isEqualTo(confirmationToken));

    }

    @Test
    void canUpdateConfirmedAt() {
        //given
        String token = "asd-agh-uio-567";
        LocalDateTime dateTime = LocalDateTime.now();
        int expected = 0;
        AppUser user = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );


        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationToken.setConfirmedAt(dateTime);

        //when
        int actual = confirmationTokenRepository.updateConfirmedAt(token, dateTime);

        //then
        if (actual == 1) {
            expected = 1;
        }
        assertThat(actual).isEqualTo(expected);


    }
}