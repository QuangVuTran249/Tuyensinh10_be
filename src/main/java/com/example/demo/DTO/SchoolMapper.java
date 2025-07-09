package com.example.demo.DTO;

import com.example.demo.entity.Truong;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SchoolMapper {
    @Mapping(source = "maTruong", target = "code")
    @Mapping(source = "tenTruong", target = "name")
    @Mapping(source = "diaChi.quanHuyen", target = "district")
    @Mapping(source = "diaChi.phuongXa", target = "ward")
    @Mapping(source = "diaChi.duong",      target = "street")
    @Mapping(source = "capKiemDinh.moTaCap", target = "accreditationLevel")
    SchoolDto toDto(Truong entity);
}
