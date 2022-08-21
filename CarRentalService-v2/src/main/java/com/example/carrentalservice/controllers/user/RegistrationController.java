package com.example.carrentalservice.controllers.user;

import com.example.carrentalservice.registerationprocess.RegistrationRequest;
import com.example.carrentalservice.registerationprocess.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController{

    private RegistrationService registrationService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER','ROLE_MANAGER')")
    public String register(@RequestBody RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest);
    }

    // TODO: can take: hasRole('ROLE_'), hasAnyRole('ROLE_'), hasAuthority('permission'),hasAnyAuthority('permission')

    @GetMapping(path = "confirm")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER','ROLE_MANAGER')")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}