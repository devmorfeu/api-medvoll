package br.com.medvoll.controller;

import br.com.medvoll.controller.dto.request.authentication.AuthenticationRequest;
import br.com.medvoll.controller.dto.request.authentication.TokenResponse;
import br.com.medvoll.entity.user.User;
import br.com.medvoll.security.TokenSecurity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenSecurity tokenSecurity;

    @PostMapping("/authentication")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {

        final var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.login(), authenticationRequest.password());

        final var authenticate = authenticationManager.authenticate(authenticationToken);

        final var token = tokenSecurity.createToken((User) authenticate.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(token));
    }
}
