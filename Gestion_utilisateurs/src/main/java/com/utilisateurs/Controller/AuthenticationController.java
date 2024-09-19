package com.utilisateurs.Controller;


import com.utilisateurs.Dto.AuthenticationRequest;
import com.utilisateurs.Dto.AuthenticationResponse;
import com.utilisateurs.Dto.RegisterRequest;
import com.utilisateurs.Service.AuthenticationService;
import com.utilisateurs.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/registerA")
    public ResponseEntity<AuthenticationResponse> registerA(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.registerA(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));

    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        jwtService.validateToken(token);
        return "Token is valid";
    }
}
