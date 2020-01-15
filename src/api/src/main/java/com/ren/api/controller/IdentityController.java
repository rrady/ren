package com.ren.api.controller;

import com.ren.api.exceptions.RenException;
import com.ren.api.model.JsonWebToken;
import com.ren.api.model.SignIn;
import com.ren.api.model.SignUp;
import com.ren.api.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class IdentityController {

    private IdentityService identityService;

    @Autowired
    public IdentityController(IdentityService identityService) {
        this.identityService = identityService;
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUp signUpModel) throws RenException {
        identityService.signUp(signUpModel.getEmail(), signUpModel.getUsername(), signUpModel.getPassword());

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<JsonWebToken> signIn(@RequestBody SignIn signInModel) throws RenException {
        JsonWebToken jsonWebToken = identityService.signIn(signInModel.getEmail(), signInModel.getPassword());

        return ResponseEntity.ok().body(jsonWebToken);
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<JsonWebToken> refresh() throws RenException {
        JsonWebToken jsonWebToken = identityService.refresh("");

        return ResponseEntity.ok().body(jsonWebToken);
    }
}