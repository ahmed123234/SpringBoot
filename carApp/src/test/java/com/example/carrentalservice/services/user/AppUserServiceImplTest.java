package com.example.carrentalservice.services.user;

import com.example.carrentalservice.configuration.security.PasswordEncoder;
import com.example.carrentalservice.exception.ApiRequestException;
import com.example.carrentalservice.models.entities.AppUser;
import com.example.carrentalservice.models.entities.ConfirmationToken;
import com.example.carrentalservice.models.entities.UserRole;
import com.example.carrentalservice.repositories.AppUserRepository;
import com.example.carrentalservice.repositories.UserRoleRepository;
import com.example.carrentalservice.services.token.ConfirmationTokenService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {

    private AppUserServiceImpl userService;
    @Mock
    UserRoleRepository userRoleRepository;

    @Mock
    AppUserRepository appUserRepository;

    @Mock
    ConfirmationTokenService tokenService;

    @Mock
    PasswordEncoder passwordEncoder;

    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found!";
    private final static String CREDENTIALS_ERROR_MESSAGE = "No such user with the given credentials %s";



    @BeforeEach
    void setUp() {
        passwordEncoder = new PasswordEncoder();
        userService = new AppUserServiceImpl(userRoleRepository, appUserRepository, passwordEncoder, tokenService);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testIfCanLoadUserByUsernameIsFalse() {
        //given
        String username = "ahmad@gmial.com";

        //when
        //then
        given(appUserRepository.findByEmail(username)).willReturn(null);
        assertThatThrownBy(()->userService.loadUserByUsername(username))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining(String.format(USER_NOT_FOUND_MESSAGE, username), HttpStatus.NOT_FOUND);

    }

    @Test
    void testIfCanLoadUserByUsernameIsAvailable() {
        //given
        String username = "ahmad@gmial.com";

        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );

        //given
        given(appUserRepository.findByEmail(username)).willReturn(actualUser);

        // when
        UserDetails user = userService.loadUserByUsername(username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        actualUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        //then
        assertThat(user).isEqualTo(new org.springframework.security.core.userdetails.User(actualUser.getUsername(),
                actualUser.getPassword(), authorities));

    }

    @Test
    void checkIfEmailIsNotUnique() {
        //given
        String email = "ahmad@gmial.com";

        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );

        //given
        given(appUserRepository.findByEmail(email)).willReturn(actualUser);

        assertThatThrownBy(()->userService.checkEmail(email))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("email already taken", HttpStatus.BAD_REQUEST);
    }

    @Test
    void checkIfEmailIsUnique() {
        //given
        String email = "ahmad@gmail.com";

        //when
        userService.checkEmail(email);

        //then
        assertThat(email).isEqualTo("ahmad@gmail.com");
    }


    @Test
    void checkIfUsernameIsNotUnique() {
        //given
        String username = "ahmad12";

        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        Optional<AppUser> optional = Optional.of(actualUser);

        //given
        given(appUserRepository.findByUsername(username)).willReturn(optional);


        assertThatThrownBy(()->userService.checkUsername(username))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("username already taken");
    }

    @Test
    void checkIfUsernameIsUnique() {
        //given
        String username = "ahmad12";

        //when
        userService.checkUsername(username);

        //then
        assertThat(username).isEqualTo("ahmad12");
    }


    @Test
    void signUpUser() {
        //given
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        String [] roles = {"ROLE_ADMIN"};

//        userService.checkEmail(actualUser.getEmail());
        checkIfEmailIsUnique();
        checkIfUsernameIsUnique();
        saveUser();

        String token = "token";
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), actualUser);
        tokenService.saveConfirmationToken(confirmationToken);

        assertThat(token).isEqualTo("token");


    }

    @Test
    void canEnableAppUser() {

        //given
        String email = "ahmad@gmail.com";

        // when
        when(appUserRepository.enableAppUser(email)).thenReturn(1);

        assertThat(userService.enableAppUser(email)).isEqualTo("user enabled successfully");

    }

    @Test
    void canNotEnableAppUser() {

        //given
        String email = "ahmad@gmail.com";

        // when
        when(appUserRepository.enableAppUser(email)).thenReturn(0);

        assertThat(userService.enableAppUser(email)).isEqualTo("Error enabling Email");

    }

    @Test
    void canGetByUserRole() {
        //given
        String userRole = "ROLE_ADMIN";
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );

        UserRole role = new UserRole(1L, "ROLE_ADMIN");

        given(userRoleRepository.findByName(userRole)).willReturn(role);
        Optional<List<AppUser>> optional = Optional.of(List.of(actualUser));

        given(appUserRepository.findByRoles(role)).willReturn(optional);
        assertThat(userService.getByUserRole(userRole)).isEqualTo(optional);
    }

    @Test
    void canNotGetByUserRole() {
        //given
        String userRole = "ROLE_ADMIN";
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );

        UserRole role = new UserRole(1L, "ROLE_ADMIN");

        given(userRoleRepository.findByName(userRole)).willReturn(role);

        Optional<List<AppUser>> optional = Optional.of(List.of());

        when(appUserRepository.findByRoles(role)).thenReturn(optional);


        assertThatThrownBy(()->userService.getByUserRole(userRole))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("No such users with the role " + userRole);

    }

    @Test
    void canGetUser() {
        //given
        String username = "ahmad1";
        String password = "201712@Asg";
        //when
        String encodedPassword= passwordEncoder.bCryptPasswordEncoder().encode(password);

        given(appUserRepository.getEncodedPassword(username)).willReturn(encodedPassword);

        assertThat(passwordEncoder.bCryptPasswordEncoder().matches(password, encodedPassword)).isEqualTo(true);

        assertThat(userService.getUser(username, password))
                .isEqualTo("The user with username :" + username + " and password: " + password + " is valid");

    }

    @Test
    void canNotGetUser() {
        //given
        String username = "ahmad1";
        String password = "201712@Asg";
        String encodedPassword= passwordEncoder.bCryptPasswordEncoder().encode("1234");
        given(appUserRepository.getEncodedPassword(username)).willReturn(encodedPassword);

        assertThat(passwordEncoder.bCryptPasswordEncoder().matches(password, encodedPassword)).isEqualTo(false);

        assertThatThrownBy(()->userService.getUser(username, password))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining(String.format(CREDENTIALS_ERROR_MESSAGE, username));

    }

    @Test
    void canGetUserOrderCount() {
        //given
        String username = "ahmad1";

        //when
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        Optional<AppUser> optional = Optional.of(actualUser);
        given(appUserRepository.findByUsername(username)).willReturn(optional);
        int expected  = 0;
//        //when
        userService.getUserOrderCount(username);

        //then
        verify(appUserRepository).getUserOrdersCount(username);

        //then
        assertThat(appUserRepository.getUserOrdersCount(username)).isEqualTo(expected);
        assertThat(userService.getUserOrderCount(username))
                .isEqualTo(0);
    }


    @Test
    void canNotGetUserOrderCount() {
        //given
        String username = "ahmad1";

        //when
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        Optional<AppUser> optional = Optional.of(actualUser);
       // given(appUserRepository.findByUsername(username)).willReturn(Optional);
        if (appUserRepository.findByUsername(username).isEmpty())
        //then
            assertThatThrownBy(()->userService.getUserOrderCount(username))
                    .isInstanceOf(ApiRequestException.class)
                    .hasMessageContaining(String.format("no such user with name: " + username + " found"));
    }


    @Test
    void getUserOrderCount() {
        //given
        String username = "ahmad1";


    }


    @Test
    void createNewUserAfterOAuthLoginSuccess() {
    }

    @Test
    void updateUserInfo() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void changeStatus() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void testDeleteUser() {
    }

    @Test
    void addUser() {
    }

    @Test
    void getUserId() {
    }

    @Test
    void updateUserPassword() {
    }

    @Test
    void getUserRole() {
    }

    @Test
    void saveRole() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void addRoleToUser() {
    }

    @Test
    void handleAuthorizationHeader() {
    }
}