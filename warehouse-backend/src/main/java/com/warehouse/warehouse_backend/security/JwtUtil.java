package com.warehouse.warehouse_backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret; // Lấy khóa bí mật từ application.yml

    @Value("${jwt.expiration}")
    private long expiration; // Lấy thời gian hết hạn từ application.yml

    // Tạo JWT token từ username và role
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username) // Đặt username vào subject
                .claim("role", role) // Thêm role vào claims
                .setIssuedAt(new Date()) // Thời gian phát hành
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Thời gian hết hạn
                .signWith(SignatureAlgorithm.HS512, secret) // Ký token bằng HS512
                .compact();
    }

    // Lấy username từ token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Lấy role từ token
    public String getRoleFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    // Kiểm tra token hợp lệ
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
