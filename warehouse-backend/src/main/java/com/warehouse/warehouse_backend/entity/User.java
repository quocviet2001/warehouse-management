package com.warehouse.warehouse_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data // Lombok: tự động tạo getter, setter, toString, v.v.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // Tên đăng nhập

    @Column(nullable = false)
    private String password; // Mật khẩu (sẽ mã hóa)

    @Column(nullable = false)
    private String role; // Vai trò: ADMIN hoặc EMPLOYEE
}