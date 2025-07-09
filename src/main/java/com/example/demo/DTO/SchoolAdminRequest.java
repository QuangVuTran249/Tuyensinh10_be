package com.example.demo.DTO;

import java.util.Set;

public record SchoolAdminRequest(
        String maTruong,
        String tenTruong,
        Short  namThanhLap,
        String website,
        String dienThoai,
        /* Địa chỉ */
        String duong,
        String phuongXa,
        String quanHuyen,
        /* Id cấp kiểm định */
        Byte    idCap,
        /* IdNgoaiNgu1, IdNgoaiNgu2 – tùy chọn */
        Byte    idNN1,
        Byte    idNN2,
        /* Danh sách IdFeature (CSVC) */
        Set<Byte> featureIds
) {}