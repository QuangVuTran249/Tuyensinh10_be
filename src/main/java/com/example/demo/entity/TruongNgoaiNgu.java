package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "truong_ngoaingu")
@Data
@NoArgsConstructor @AllArgsConstructor
public class TruongNgoaiNgu {

    @EmbeddedId
    private TruongNgoaiNguKey id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("maTruong")              // khớp với trường trong khóa tổng hợp
    @JoinColumn(name = "MaTruong")   // tên cột FK trong bảng truong_ngoaingu
    private Truong truong;
    public TruongNgoaiNguKey getId() {
        return id;
    }

    public void setId(TruongNgoaiNguKey id) {
        this.id = id;
    }
}