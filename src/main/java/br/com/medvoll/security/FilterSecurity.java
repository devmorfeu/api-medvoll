package br.com.medvoll.security;

import br.com.medvoll.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
@RequiredArgsConstructor
public class FilterSecurity extends OncePerRequestFilter {

    private final TokenSecurity tokenSecurity;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        getSubjectToken(request);

        filterChain.doFilter(request, response);
    }

    private void getSubjectToken(HttpServletRequest request) {

        final var token = request.getHeader("Authorization");

        if (token != null) {

            final var email = tokenSecurity.getSubjectToken(token.replace("Bearer", ""));

            final var user = userRepository.findByLogin(email);

            final var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            getContext().setAuthentication(authentication);
        }
    }
}
