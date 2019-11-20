package io.ren.api.security.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.ren.api.security.service.JwtTokenService;
import io.ren.api.repository.UserRepository;

public class JwtTokenFilter extends OncePerRequestFilter {
    private String header = "Authorization";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        getToken(request.getHeader(header)).ifPresent(token -> {
            jwtTokenService.getSubject(token).ifPresent(id -> {
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    userRepository.findById(id).ifPresent(user -> {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                Collections.emptyList()
                        );
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    });
                }
            });
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
