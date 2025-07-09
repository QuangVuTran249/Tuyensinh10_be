package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "DiemChuanC_TH",
       uniqueConstraints = @UniqueConstraint(name = "uq_diemchuan_cth",
                     columnNames = {"MaTruong","Nam","IdLoaiCTH"}))
@NoArgsConstructor @AllArgsConstructor
public class DiemChuanCth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaTruong", nullable = false)
    private Truong truong;

    @Column(name = "Nam", nullable = false)
    private Short nam;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdLoaiCTH", nullable = false)
    private LoaiChuyenTichHop loai;   // Ngữ văn, Toán, Tích hợp…

    @Column(name="DiemNV1") private BigDecimal diemNV1;
    @Column(name="DiemNV2") private BigDecimal diemNV2;
    @Column(name="DiemNV3") private BigDecimal diemNV3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Truong getTruong() {
        return truong;
    }

    public void setTruong(Truong truong) {
        this.truong = truong;
    }

    public Short getNam() {
        return nam;
    }

    public void setNam(Short nam) {
        this.nam = nam;
    }

    public LoaiChuyenTichHop getLoai() {
        return loai;
    }

    public void setLoai(LoaiChuyenTichHop loai) {
        this.loai = loai;
    }

    public BigDecimal getDiemNV1() {
        return diemNV1;
    }

    public void setDiemNV1(BigDecimal diemNV1) {
        this.diemNV1 = diemNV1;
    }

    public BigDecimal getDiemNV2() {
        return diemNV2;
    }

    public void setDiemNV2(BigDecimal diemNV2) {
        this.diemNV2 = diemNV2;
    }

    public BigDecimal getDiemNV3() {
        return diemNV3;
    }

    public void setDiemNV3(BigDecimal diemNV3) {
        this.diemNV3 = diemNV3;
    }
}
