package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "diemchuan")  // đúng tên trong file SQL
@Data @NoArgsConstructor @AllArgsConstructor
public class DiemChuan {

    @EmbeddedId
    private DiemChuanKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("maTruong")
    @JoinColumn(name = "MaTruong")
    private Truong truong;

    @Column(name = "DiemNV1")
    private BigDecimal diemNV1;

    @Column(name = "DiemNV2")
    private BigDecimal diemNV2;

    @Column(name = "DiemNV3")
    private BigDecimal diemNV3;

    @Column(name = "ChiTieu")
    private Integer chiTieu;

    @Column(name = "TyLeChoi")
    private BigDecimal tyLeChoi;

    public DiemChuanKey getId() {
        return id;
    }

    public void setId(DiemChuanKey id) {
        this.id = id;
    }

    public Truong getTruong() {
        return truong;
    }

    public void setTruong(Truong truong) {
        this.truong = truong;
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

    public Integer getChiTieu() {
        return chiTieu;
    }

    public void setChiTieu(Integer chiTieu) {
        this.chiTieu = chiTieu;
    }

    public BigDecimal getTyLeChoi() {
        return tyLeChoi;
    }

    public void setTyLeChoi(BigDecimal tyLeChoi) {
        this.tyLeChoi = tyLeChoi;
    }
}
