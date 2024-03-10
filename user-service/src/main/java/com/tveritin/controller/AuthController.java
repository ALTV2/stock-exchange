package com.tveritin.controller;

import com.tveritin.model.dto.LoginUserDto;
import com.tveritin.model.dto.RegisterUserDto;
import com.tveritin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi{
    private final AuthService authService;

    @Override
    public ResponseEntity<Void> login(LoginUserDto loginUser) {
        return null;
    }

    @Override
    public ResponseEntity<Void> register(RegisterUserDto registerUser) {
        return null;
    }
}

