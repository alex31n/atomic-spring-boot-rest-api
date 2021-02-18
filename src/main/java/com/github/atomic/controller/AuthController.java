package com.github.atomic.controller;


import com.github.atomic.model.login.LoginRequest;
import com.github.atomic.model.signup.SignupRequest;
import com.github.atomic.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@Tag(name = "Auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @Operation(summary = "Login")
    @PostMapping(value = "login", produces = "application/json")
    public ResponseEntity<?> login(@Valid  @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @Operation(summary = "Signup")
    @PostMapping(value = "signup", produces = "application/json")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
        return authService.signup(request);
    }


}
