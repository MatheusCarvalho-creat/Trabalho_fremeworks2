package com.grupo.filmes.controller;

import com.grupo.filmes.dto.AuthRequest;
import com.grupo.filmes.dto.AuthResponse;
import com.grupo.filmes.model.User;

import com.grupo.filmes.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
  

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userService=userService; this.passwordEncoder=passwordEncoder; this.jwtUtil=jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User u){
        if(userService.findByUsername(u.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Username already exists");
        }
        User created = userService.register(u);
        created.setPassword(null);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req){
        return userService.findByUsername(req.getUsername())
                .map(user -> {
                    if(passwordEncoder.matches(req.getPassword(), user.getPassword())){
                        String token = jwtUtil.generateToken(user.getUsername());
                        return ResponseEntity.ok(new AuthResponse(token));
                    } else {
                        return ResponseEntity.status(401).body("Invalid credentials");
                    }
                }).orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
}
