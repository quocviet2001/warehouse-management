package com.warehouse.warehouse_backend.controller;
import com.warehouse.warehouse_backend.dto.UserDTO;
import com.warehouse.warehouse_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}