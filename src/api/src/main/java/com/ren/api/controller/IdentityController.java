package com.ren.api.controller;

import com.ren.api.model.ChangePasswordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ren.api.exceptions.RenException;
import com.ren.api.model.JsonWebToken;
import com.ren.api.model.SignIn;
import com.ren.api.model.SignUp;
import com.ren.api.service.IdentityService;

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

    @PostMapping(value = "/{refreshToken}/refresh")
    public ResponseEntity<JsonWebToken> refresh(@PathVariable("refreshToken") String refreshToken) throws RenException {
        JsonWebToken jsonWebToken = identityService.refresh(refreshToken);

        return ResponseEntity.ok().body(jsonWebToken);
    }

    @PutMapping(value = "/reset")
    public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordModel model) throws RenException {
        identityService.changePassword(model.getUserId(), model.getCurrentPassword(), model.getNewPassword());
        return ResponseEntity.noContent().build();
    }
}