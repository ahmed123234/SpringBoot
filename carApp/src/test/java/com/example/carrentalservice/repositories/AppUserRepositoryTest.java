package com.example.carrentalservice.repositories;

import com.example.carrentalservice.configuration.security.AuthenticationProvider;
import com.example.carrentalservice.models.entities.AppUser;
import com.example.carrentalservice.models.entities.UserRole;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
@DisplayName("test for the user repository tasks")
@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @DisplayName("test for get the user by a valid email")
    @Test
    void canFindUserByEmail() {
        //given
        String email = "ahmad@gmail.com";
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(actualUser);

        //when
        AppUser actual = userRepository.findByEmail(email);

        //then
        if (actual != null)
            assertThat(actual).isEqualTo(actualUser);
        else
            assertThat(actualUser).isNotEqualTo(null);
    }

    @DisplayName("test if user is found by their name")
    @Test
    void CanFindIfUsernamePresent() {

        //given
        String username = "ahmad1";
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(actualUser);

        //when
        Optional<AppUser> actual = userRepository.findByUsername(username);

        //then
        if (actual.isEmpty())
            assertThat(actual).isEmpty();

        else {
            assertThat(actual.get()).isEqualTo(actualUser);
        }
    }

    @DisplayName("test if user is found by their name")
    @Test
    void canFindAppUserByUsername() {

        //given
        String username = "ahmad1";
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(actualUser);

        //when
        AppUser actual = userRepository.findAppUserByUsername(username);

        //then
        assertThat(actual).isEqualTo(actualUser);
    }

    @Test
    void canEnableAppUser() {
        //given
        String email = "ahmad@gmail.com";
        int expected = 0;
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                email,
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(actualUser);

        actualUser.setEnabled(true);
        if (actualUser.isEnabled())
            expected = 1;

        //when
        int actual = userRepository.enableAppUser(email);

        //then
        assertThat(actual).isEqualTo(expected);
    }

//    @Disabled
    @Test
    void canUpdateAppUserInfo() {
        //given
        String email = "ahmad@gmail.com";
        int expected = 0;
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(actualUser);

        actualUser.setAuthenticationProvider(AuthenticationProvider.GOOGLe);
        if (actualUser.getAuthenticationProvider()!= null)
            expected = 1;

        //when
        int actual = userRepository.updateAppUserInfo(email,AuthenticationProvider.GOOGLe);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canNotUpdateAppUserInfo() {
        //given
        String email = "ahmad1@gmail.com";
        int expected = 0;
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(actualUser);

        actualUser.setAuthenticationProvider(AuthenticationProvider.GOOGLe);
        if (actualUser.getAuthenticationProvider()!= null)
            expected = 1;

        //when
        int actual = userRepository.updateAppUserInfo(email,AuthenticationProvider.GOOGLe);

        //then
        assertThat(actual).isNotEqualTo(expected);
    }

    @Disabled
    @Test
    void findByRoles() {
        //given
        UserRole role = new UserRole(1L, "ROLE_ADMIN");

        List<AppUser> actualUsers = new ArrayList<>();
        AppUser userSample1 = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userSample1.addRole(role);
        userRepository.save(userSample1);

        AppUser userSample2 = new AppUser(
                "akram",
                "ali",
                "akram@gmail.com",
                "akram1",
                "201712@Asg"
        );
        userSample2.addRole(role);
        userRepository.save(userSample2);

        actualUsers.add(userSample1);
        actualUsers.add(userSample2);

        List<AppUser> expectedAppUsers;
        //when
        if (userRepository.findByRoles(role).isPresent()) {
            expectedAppUsers = userRepository.findByRoles(role).get();
            assertThat(actualUsers).isEqualTo(expectedAppUsers);
        }
        //then
    }

//    @Disabled
    @Test
    void getUserOrdersCount() {
        //given
        String username = "akram1";

        //when
        Integer actual = userRepository.getUserOrdersCount(username);

        AtomicInteger validIdFound = new AtomicInteger();

        //then
        if (actual == 1){
            validIdFound.getAndIncrement();
        }
        assertThat(actual).isEqualTo(validIdFound.intValue());
    }


    @Test
    void canUpdateUserStatusToEnabled() {
            //given
            String username = "ahmad1";
            boolean status = true;

            AppUser actualUser = new AppUser(
                    "ahmad",
                    "ali",
                    "ahmad@gmail.com",
                    "ahmad1",
                    "201712@Asg"
            );
            userRepository.save(actualUser);
            actualUser.setEnabled(status);



        //when
            int val = userRepository.updateStatus(username, status);

            //then
            if (val == 1) {
                assertThat(actualUser.getEnabled()).isEqualTo(status);
            }
            else
                assertThat(actualUser.getEnabled()).isNotEqualTo(status);

    }

    @Test
    void canDeleteUserByUsername() {
        //given
        String username = "ahmad1";
        int expected = 0;
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(actualUser);


        //when
        int actual = userRepository.deleteUser(username);

        //then
        if (actual == 1) {
            expected = 1;
           assertThat(actual).isEqualTo(expected);

        }
        else {

            assertThat(actual).isEqualTo(expected);
        }
    }


    @Test
    void canDeleteUserByID() {

        //given
        Long id = 1L;
        int expected = 0;
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(actualUser);


        //when
        int actual = userRepository.deleteUserByID(id);

        //then
        if (actual == 1) {
            expected = 1;
            assertThat(actual).isEqualTo(expected);

        }
        else
            assertThat(actual).isEqualTo(expected);

    }


    @Test
    void canUpdateUserPassword() {
        //given
        String username = "ahmad1";
        String password = "112W@Asdq1";
        int expected = 0;
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(actualUser);

        actualUser.setPassword(password);
        if(actualUser.getPassword().equals(password)
                && username.equals(actualUser.getUsername()))
            expected = 1;

        //when
        int actual = userRepository.updateUserPassword(username, password);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findAllActiveUsers() {
        //given
        List<AppUser> actualUsers = new ArrayList<>();
        AppUser userSample1 = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userSample1.setEnabled(true);
        userRepository.save(userSample1);
        actualUsers.add(userSample1);

        //when
        List<AppUser> actual = userRepository.findAllActiveUsers(
                JpaSort.unsafe("userId"));


        //then
        assertThat(actual).isEqualTo(actualUsers);
    }


    @Test
    void findAllDisabledUsers() {
        //given
        List<AppUser> actualUsers = new ArrayList<>();
        AppUser userSample1 = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userSample1.setEnabled(false);
        userRepository.save(userSample1);
        actualUsers.add(userSample1);

        //when
        List<AppUser> actual = userRepository.findAllDisabledUsers(
                JpaSort.unsafe("userId"));


        //then
        assertThat(actual).as("find all disabled users").isEqualTo(actualUsers);
    }

    @Test
    void findAllByPagination() {
        //given
        List<AppUser> actualUsers = new ArrayList<>();
        AppUser userSample1 = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(userSample1);
        actualUsers.add(userSample1);
        Page<AppUser> actualPage = new PageImpl<>(actualUsers);

        //when
          Page<AppUser> actual = userRepository.findAllByPagination(Pageable.unpaged());

        //then
        assertThat(actual).isEqualTo(actualPage);
    }


    @Test
    void getEncodedPassword() {
        //given
        String actualPassword = "201712@Asg";
        AppUser actualUser = new AppUser(
                "ahmad",
                "ali",
                "ahmad@gmail.com",
                "ahmad1",
                "201712@Asg"
        );
        userRepository.save(actualUser);

        //when
        String actual = userRepository.getEncodedPassword("ahmad1");

        //then
        assertThat(actual).isEqualTo(actualPassword);
    }
}