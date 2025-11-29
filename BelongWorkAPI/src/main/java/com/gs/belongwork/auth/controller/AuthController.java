package com.gs.belongwork.auth.controller;

import com.gs.belongwork.auth.model.Role;
import com.gs.belongwork.auth.model.User;
import com.gs.belongwork.auth.repository.UserRepository;
import com.gs.belongwork.config.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body("Usuário não encontrado");
        String token = jwtUtil.generateToken(userOpt.get());
        return ResponseEntity.ok(new java.util.HashMap<String,Object>(){{ put("token", token); put("username", request.getUsername()); put("type","Bearer"); }});
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) return ResponseEntity.badRequest().body("Username já existe");
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(Role.ROLE_CANDIDATE));
        userRepository.save(user);
        return ResponseEntity.ok("Usuário criado");
    }
}
