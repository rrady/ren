package io.ren.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ren.api.exception.RenException;
import io.ren.api.security.service.AuthenticationService;

import io.ren.api.security.model.RegisterRequest;
import io.ren.api.security.model.AuthenticationRequest;
import io.ren.api.security.model.AuthenticationResponse;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) throws RenException {
        authenticationService.register(registerRequest.getEmail(), registerRequest.getUsername(), registerRequest.getPassword());

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/auth/authenticate", method = RequestMethod.POST)
    public ResponseEntity authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws RenException {
        String token = authenticationService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        AuthenticationResponse responseBody = new AuthenticationResponse();
        responseBody.setAccessToken(token);

        return ResponseEntity.ok().body(responseBody);
    }
}
