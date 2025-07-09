package com.example.demo.DTO;

import com.example.demo.entity.DiemChuanCth;

import java.math.BigDecimal;

public record DiemChuanCthDto(
    Integer id,
    String maTruong,
    String tenTruong,
    Short nam,
    String loai,
    BigDecimal diemNV1,
    BigDecimal diemNV2,
    BigDecimal diemNV3
) {
    public static DiemChuanCthDto of(DiemChuanCth d) {
        return new DiemChuanCthDto(
            d.getId(),
            d.getTruong().getMaTruong(),
            d.getTruong().getTenTruong(),
            d.getNam(),
            d.getLoai().getTenLoai(),
            d.getDiemNV1(),
            d.getDiemNV2(),
            d.getDiemNV3()
        );
    }
}
