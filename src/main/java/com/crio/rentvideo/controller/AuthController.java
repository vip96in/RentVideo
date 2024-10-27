package com.crio.rentvideo.controller;

import com.crio.rentvideo.dto.UserDTO;
import com.crio.rentvideo.model.User;
import com.crio.rentvideo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User registeredUser = authService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        String token = authService.loginUser(userDTO);
        return ResponseEntity.ok(token);
    }
}
