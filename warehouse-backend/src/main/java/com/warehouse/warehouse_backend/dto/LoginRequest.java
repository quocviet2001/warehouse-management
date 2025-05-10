package com.warehouse.warehouse_backend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username; // Tên đăng nhập
    private String password; // Mật khẩu
}
