package com.warehouse.warehouse_backend.service;

import com.warehouse.warehouse_backend.dto.LoginRequest;
import com.warehouse.warehouse_backend.dto.LoginResponse;
import com.warehouse.warehouse_backend.entity.User;
import com.warehouse.warehouse_backend.repository.UserRepository;
import com.warehouse.warehouse_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        // Xác thực username và password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Lưu authentication vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Tìm user trong database
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Tạo JWT token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        // Trả về response chứa token
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return response;
    }
}
