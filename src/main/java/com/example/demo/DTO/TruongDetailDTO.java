package com.example.demo.DTO;

import java.util.List;

public record TruongDetailDTO(
        String maTruong,
        String tenTruong,
        Short namThanhLap,
        String website,
        String dienThoai,
        DiaChiDTO diaChi,
        String capKiemDinh,
        List<String> features,
        List<DiemChuanDTO> diemChuans) {}