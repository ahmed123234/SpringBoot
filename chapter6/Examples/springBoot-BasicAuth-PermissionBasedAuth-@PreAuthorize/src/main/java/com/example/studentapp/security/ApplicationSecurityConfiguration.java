package com.example.studentapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.studentapp.security.ApplicationUserRules.*;

// basic auth: the username and password is sent on every single request.
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // to be understood.
                .authorizeRequests()
                //.antMatchers("/","index","/css/*", "js/*")
                //.permitAll()
                .antMatchers("/api/**").hasRole(STUDENt.name())
                //.antMatchers("/management/**").hasRole(ADMIN.name())
                .anyRequest().authenticated()
                .and().httpBasic();
    }
     // inmemory user details manager
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails OmarUser = User.builder()
                .username("Omar")
                .password(passwordEncoder.encode("password"))
                .roles("Student") // it will be internally stores as ROLE_STUDENT
                .build();

        UserDetails AhmadUser=User.builder()
                .username("Ahmad")
                .password(passwordEncoder.encode("123a"))
                .roles(STUDENt.name())
                .build();

        UserDetails lindaUser=User.builder()
                .username("Linda")
                .password(passwordEncoder.encode("1234"))
                .roles(ADMIN.name())
                .build();

        UserDetails ZaherUser=User.builder()
                .username("Zaher")
                .password(passwordEncoder.encode("2222"))
                .roles(ADMIN_TRAINEE.name()) //
                .build();

        return new InMemoryUserDetailsManager(
                OmarUser,
                AhmadUser,
                lindaUser,
                ZaherUser
        );
    }

}
