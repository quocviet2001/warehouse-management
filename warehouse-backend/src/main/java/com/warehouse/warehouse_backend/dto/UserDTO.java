package com.warehouse.warehouse_backend.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username; // Tên đăng nhập
    private String password; // Mật khẩu
    private String role; // Vai trò: ADMIN hoặc EMPLOYEE
}