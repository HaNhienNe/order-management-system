package com.sangdd.omsbe.controller;

import com.sangdd.omsbe.dto.request.LoginRequest;
import com.sangdd.omsbe.dto.response.AuthResponse;
import com.sangdd.omsbe.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Xác thực tài khoản với Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // 2. Nếu thành công, tạo Token dựa trên Username và Role
        // (Lưu ý: Bạn có thể lấy Role từ đối tượng authentication ở trên)
        String token = jwtService.generateToken(request.getUsername());

        // 3. Trả về Token cho Angular
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
