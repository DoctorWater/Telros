package ru.malkov.telrostesttask.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.malkov.telrostesttask.dto.AuthDto;

@RestController
public class SecurityController {
    AuthenticationProvider authenticationProvider;


    public SecurityController(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @PostMapping(value = "/login")
    public void authenticate(@RequestBody AuthDto dto){
      authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(dto.username(),dto.password()));
    }
}
