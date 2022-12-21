package br.com.medvoll.security;

import br.com.medvoll.entity.user.User;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.auth0.jwt.JWT.*;
import static com.auth0.jwt.algorithms.Algorithm.*;
import static java.time.LocalDateTime.now;
import static java.time.ZoneOffset.of;

@Component
public class TokenSecurity {

    @Value("${api.security.token.secret}")
    private String secret;

    public String getSubjectToken(String token) {

        final var encrypt = HMAC256(secret);

        return JWT.require(encrypt)
                .withIssuer("API medVoll")
                .build()
                .verify(token)
                .getSubject();
    }

    public String createToken(User user) {

        final var encrypt = HMAC256(secret);

        return create()
                .withIssuer("API medVoll")
                .withSubject(user.getLogin())
                .withClaim("id", user.getUsername())
                .withExpiresAt(expirationDate())
                .sign(encrypt);
    }

    private Instant expirationDate() {
        return now().plusHours(2).toInstant(of("-03:00"));
    }
}
