package com.ren.api.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import com.ren.api.security.AccessTokenProvider;

public class JwtFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String SUBJECT_KEY = "sub";
    private static final String AUTHORITIES_KEY = "auth";

    private AccessTokenProvider accessTokenProvider;

    @Autowired
    public JwtFilter(AccessTokenProvider accessTokenProvider) {
        this.accessTokenProvider = accessTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws ServletException, IOException {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        getJwt(httpServletRequest).ifPresent(token -> {
            if (this.accessTokenProvider.validateToken(token)) {
                Authentication authentication = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        });

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Optional<String> getJwt(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader(AUTHORIZATION_HEADER);

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

    private Authentication getAuthentication(String token) {
        Map<String, Object> claims = this.accessTokenProvider.getClaims(token);

        // Collection<? extends GrantedAuthority> authorities =
        //     Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        //         .map(SimpleGrantedAuthority::new)
        //         .collect(Collectors.toList());

        User principal = new User(claims.get(SUBJECT_KEY).toString(), "", /*authorities*/ Collections.emptyList());

        return new UsernamePasswordAuthenticationToken(principal, token, /*authorities*/ Collections.emptyList());
    }
}