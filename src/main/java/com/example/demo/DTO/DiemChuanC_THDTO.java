package com.example.demo.DTO;

import java.math.BigDecimal;

public record DiemChuanC_THDTO(
    Long id,
    String maTruong,
    short nam,
    String loai, // VD: "CHUYEN_TOAN"
    BigDecimal diemNV1,
    BigDecimal diemNV2,
    BigDecimal diemNV3
) {}
