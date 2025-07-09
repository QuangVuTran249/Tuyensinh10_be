package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HinhThucTuyenSinh")
@Data @NoArgsConstructor @AllArgsConstructor
public class HinhThucTuyenSinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHinhThuc")
    private Byte idHinhThuc;

    @Column(name = "TenHinhThuc", length = 30, nullable = false)
    private String tenHinhThuc;
}
