package com.example.studentapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.studentapp.security.ApplicationUserPermission.COURSE_WRITE;
import static com.example.studentapp.security.ApplicationUserRules.*;

// basic auth: the username and password is sent on every single request.
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//TODO: to configure @PreAuthorize.
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // TODO: to be learned in the next section.
                .authorizeRequests()
//                .antMatchers("/","index","/css/*", "js/*")
//                .permitAll()
                // ToDO: Rule based Authentication
                .antMatchers("/api/**").hasRole(STUDENT.name())
                // ToDO: Permission based Authentication using antMatchers
                .antMatchers(HttpMethod.DELETE,"/management/api/**")
                .hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/management/api/**")
                .hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/management/api/**")
                .hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers( HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(),ADMIN_TRAINEE.name())
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
                //.roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails lindaUser=User.builder()
                .username("Linda")
                .password(passwordEncoder.encode("1234"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())

                .build();

        UserDetails ZaherUser=User.builder()
                .username("Zaher")
                .password(passwordEncoder.encode("2222"))
//                .roles(ADMIN_TRAINEE.name())
                .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                OmarUser,
                AhmadUser,
                lindaUser,
                ZaherUser
        );
    }

}
