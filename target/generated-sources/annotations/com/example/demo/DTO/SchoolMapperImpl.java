package com.example.demo.DTO;

import com.example.demo.entity.CapKiemDinh;
import com.example.demo.entity.DiaChi;
import com.example.demo.entity.Truong;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-19T06:49:13+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class SchoolMapperImpl implements SchoolMapper {

    @Override
    public SchoolDto toDto(Truong entity) {
        if ( entity == null ) {
            return null;
        }

        String code = null;
        String name = null;
        String district = null;
        String ward = null;
        String street = null;
        String accreditationLevel = null;
        String website = null;

        code = entity.getMaTruong();
        name = entity.getTenTruong();
        district = entityDiaChiQuanHuyen( entity );
        ward = entityDiaChiPhuongXa( entity );
        street = entityDiaChiDuong( entity );
        accreditationLevel = entityCapKiemDinhMoTaCap( entity );
        website = entity.getWebsite();

        Short foundedYear = null;
        String phone = null;

        SchoolDto schoolDto = new SchoolDto( code, name, district, ward, street, accreditationLevel, foundedYear, phone, website );

        return schoolDto;
    }

    private String entityDiaChiQuanHuyen(Truong truong) {
        if ( truong == null ) {
            return null;
        }
        DiaChi diaChi = truong.getDiaChi();
        if ( diaChi == null ) {
            return null;
        }
        String quanHuyen = diaChi.getQuanHuyen();
        if ( quanHuyen == null ) {
            return null;
        }
        return quanHuyen;
    }

    private String entityDiaChiPhuongXa(Truong truong) {
        if ( truong == null ) {
            return null;
        }
        DiaChi diaChi = truong.getDiaChi();
        if ( diaChi == null ) {
            return null;
        }
        String phuongXa = diaChi.getPhuongXa();
        if ( phuongXa == null ) {
            return null;
        }
        return phuongXa;
    }

    private String entityDiaChiDuong(Truong truong) {
        if ( truong == null ) {
            return null;
        }
        DiaChi diaChi = truong.getDiaChi();
        if ( diaChi == null ) {
            return null;
        }
        String duong = diaChi.getDuong();
        if ( duong == null ) {
            return null;
        }
        return duong;
    }

    private String entityCapKiemDinhMoTaCap(Truong truong) {
        if ( truong == null ) {
            return null;
        }
        CapKiemDinh capKiemDinh = truong.getCapKiemDinh();
        if ( capKiemDinh == null ) {
            return null;
        }
        String moTaCap = capKiemDinh.getMoTaCap();
        if ( moTaCap == null ) {
            return null;
        }
        return moTaCap;
    }
}
