package io.ren.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.ren.api.dto.JsonWebToken;
import io.ren.api.model.SignIn;
import io.ren.api.model.SignUp;
import io.ren.api.exception.RenException;
import io.ren.api.security.service.IdentityService;

@RestController
public class IdentityController {
    @Autowired
    private IdentityService identityService;

    @PostMapping(value = "/auth/sign-up")
    public ResponseEntity signUp(@RequestBody SignUp signUpModel) throws RenException {
        identityService.signUp(signUpModel.getEmail(), signUpModel.getUsername(), signUpModel.getPassword());

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/auth/sign-in")
    public ResponseEntity signIn(@RequestBody SignIn signInModel) throws RenException {
        JsonWebToken jsonWebToken = identityService.signIn(signInModel.getEmail(), signInModel.getPassword());

        return ResponseEntity.ok().body(jsonWebToken);
    }
}
