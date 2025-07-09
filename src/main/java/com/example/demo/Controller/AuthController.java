package com.example.demo.Controller;

import com.example.demo.DTO.JwtResponse;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.SignupRequest;
import com.example.demo.Repository.UserRepository;
import com.example.demo.config.JwtTokenProvider;
import com.example.demo.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder pwEncoder;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(
        UserRepository userRepo,
        PasswordEncoder pwEncoder,
        AuthenticationManager authManager,
        JwtTokenProvider tokenProvider
    ) {
        this.userRepo     = userRepo;
        this.pwEncoder    = pwEncoder;
        this.authManager  = authManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest req) {
        if (userRepo.findByUsername(req.username()).isPresent()) {
            return ResponseEntity.badRequest()
                                 .body("Username đã tồn tại!");
        }
        User u = new User();
        u.setUsername(req.username());
        u.setPassword(pwEncoder.encode(req.password()));
        userRepo.save(u);
        return ResponseEntity.ok("Đăng ký thành công!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                req.username(),
                req.password()
            )
        );
        String token = tokenProvider.createToken(req.username());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
