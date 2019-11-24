package io.ren.api.security.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.ren.api.repository.UserRepository;
import io.ren.api.security.handler.JwtHandler;

public class JwtTokenFilter extends OncePerRequestFilter {
    private String HEADER = "Authorization";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtHandler jwtHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        getToken(request.getHeader(HEADER)).ifPresent(token -> {
            if (jwtHandler.verifyToken(token)) {
                String subject = jwtHandler.getClaims(token).getSubject();
                userRepository.findByEmail(subject).ifPresent(user -> {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user, token, Collections.emptyList());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
            }
        });

        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(String header) {
        if (header == null) {
            return Optional.empty();
        }

        String[] split = header.split(" ");
        if (split.length < 2) {
            return Optional.empty();
        } else {
           return Optional.ofNullable(split[1]);
        }
    }
}
