package com.example.demo.DTO;

import java.math.BigDecimal;

public record DiemChuanDTO(
        Short nam,
        BigDecimal diemNV1,
        BigDecimal diemNV2,
        BigDecimal diemNV3,
        Integer chiTieu,
        BigDecimal tyLeChoi) {}