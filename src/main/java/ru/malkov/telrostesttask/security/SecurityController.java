package ru.malkov.telrostesttask.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.malkov.telrostesttask.dto.AuthDto;

@RestController

public class SecurityController {
    AuthenticationManager authenticationManager;


    public SecurityController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/login")
    public void authenticate(@RequestBody AuthDto dto){
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(),dto.password()));
    }

    @GetMapping(value = "/login/check")
    public Authentication check(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
