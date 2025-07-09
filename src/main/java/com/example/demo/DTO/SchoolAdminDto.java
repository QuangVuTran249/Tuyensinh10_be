package com.example.demo.DTO;

import com.example.demo.entity.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record SchoolAdminDto(
        String  maTruong,
        String  tenTruong,
        Short   namThanhLap,
        String  website,
        String  dienThoai,
        String  duong,
        String  phuongXa,
        String  quanHuyen,
        String  capKiemDinh,
        Set<String> features,
        List<String> ngoaiNgus
) {
    public static SchoolAdminDto of(Truong t) {
        return new SchoolAdminDto(
                t.getMaTruong(),
                t.getTenTruong(),
                t.getNamThanhLap(),
                t.getWebsite(),
                t.getDienThoai(),
                t.getDiaChi().getDuong(),
                t.getDiaChi().getPhuongXa(),
                t.getDiaChi().getQuanHuyen(),
                t.getCapKiemDinh().getMoTaCap(),
                t.getFeatures() == null ? Set.of() :
                        t.getFeatures().stream().map(Feature::getTenFeature).collect(Collectors.toSet()),
                t.getNgoaiNgus() == null ? List.of() :
                        t.getNgoaiNgus().stream().map(NgoaiNgu::getTenNN).toList()
        );
    }

}