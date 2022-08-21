package com.example.carrentalservice.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String userName);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    void enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.authenticationProvider =?2 WHERE a.email = ?1")
    void updateAppUserInfo(String email, AuthenticationProvider provider);

    Optional<List<AppUser>> findByAppUserRole(AppUserRole appUserRole);
    Optional <AppUser> findByUsernameAndPassword(String username, String password);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled =?2 WHERE a.userId = ?1")
    void UpdateStatus(Long userId, boolean status);

    @Transactional
    @Modifying
    @Query("DELETE FROM AppUser a " +
            "WHERE a.userId = ?1")
    void deleteUser(Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET  a.password = ?2 " +
            "WHERE a.username = ?1")
    void updateUserPassword(String username, String password);
}