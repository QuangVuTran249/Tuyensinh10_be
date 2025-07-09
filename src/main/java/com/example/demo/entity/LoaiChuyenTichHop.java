package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LoaiChuyenTichHop")
@NoArgsConstructor
@AllArgsConstructor
public class LoaiChuyenTichHop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLoaiCTH")
    private Byte id;

    @Column(name = "TenLoai", length = 50, nullable = false, unique = true)
    private String tenLoai;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
