package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
    @NotBlank(message = "Username không được để trống")
    String username,

    @NotBlank(message = "Password không được để trống")
    String password
) {}
