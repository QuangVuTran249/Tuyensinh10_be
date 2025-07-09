package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JwtResponse(
    @JsonProperty("token")
    String token
) {}
