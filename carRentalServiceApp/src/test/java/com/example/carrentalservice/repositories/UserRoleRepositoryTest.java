package com.example.carrentalservice.repositories;

import com.example.carrentalservice.models.entities.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRoleRepositoryTest {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    void canFindRoleByName() {
        //given
        String roleName = "ROLE_ADMIN";
        UserRole actualRole = new UserRole(null, roleName);
        userRoleRepository.save(actualRole);

        //when
        UserRole expectedRole = userRoleRepository.findByName(roleName);

        //then
        assertThat(actualRole).isEqualTo(expectedRole);
    }
}