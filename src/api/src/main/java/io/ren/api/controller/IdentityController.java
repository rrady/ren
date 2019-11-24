package io.ren.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ren.api.dto.JsonWebToken;
import io.ren.api.model.SignIn;
import io.ren.api.model.SignUp;
import io.ren.api.exception.RenException;
import io.ren.api.security.service.IdentityService;

@RestController
public class IdentityController {
    @Autowired
    private IdentityService identityService;

    @RequestMapping(value = "/auth/sign-up", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody SignUp signUpModel) throws RenException {
        identityService.signUp(signUpModel.getEmail(), signUpModel.getUsername(), signUpModel.getPassword());

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/auth/sign-in", method = RequestMethod.POST)
    public ResponseEntity signIn(@RequestBody SignIn signInModel) throws RenException {
        JsonWebToken jsonWebToken = identityService.signIn(signInModel.getEmail(), signInModel.getPassword());

        return ResponseEntity.ok().body(jsonWebToken);
    }
}
