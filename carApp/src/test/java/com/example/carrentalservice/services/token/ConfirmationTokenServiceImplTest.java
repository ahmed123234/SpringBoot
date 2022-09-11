package com.example.carrentalservice.services.token;

import com.example.carrentalservice.models.entities.AppUser;
import com.example.carrentalservice.models.entities.ConfirmationToken;
import com.example.carrentalservice.repositories.ConfirmationTokenRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConfirmationTokenServiceImplTest {

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;
    private ConfirmationTokenServiceImpl tokenService;

    @BeforeEach
    void setUp() {
    tokenService = new ConfirmationTokenServiceImpl(confirmationTokenRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void canSaveConfirmationToken() {
        //given
        ConfirmationToken confirmationToken = new ConfirmationToken(
                "sdd-h123-oo",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                new AppUser(
                        "ahmad",
                        "ali",
                        "ahmad@gmail.com",
                        "ahmad1",
                        "201712@Asg"
                )
        );

        //when
        tokenService.saveConfirmationToken(confirmationToken);

        //then
        verify(confirmationTokenRepository).save(confirmationToken);
    }

    @Test
    void canGetToken() {
        //given
        String token = "sdd-h123-oo";

        //when
        tokenService.getToken(token);

        //then
        verify(confirmationTokenRepository).findByToken(token);
    }

    @Test
    void setConfirmedAt() {
        //given
        String token = "sdd-h123-oo";

        //when
        tokenService.setConfirmedAt(token);

        //then
        verify(confirmationTokenRepository).updateConfirmedAt(token,
                LocalDateTime.now());
    }
}