package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name="diachi",
       uniqueConstraints=@UniqueConstraint(name="uq_fulladdr",
                     columnNames={"Duong","PhuongXa","QuanHuyen"}))
@Data @NoArgsConstructor
public class DiaChi {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IdDiaChi")                               private Integer idDiaChi;

    @Column(nullable=false, length=100)                    private String duong;
    @Column(nullable=false, length=50)                     private String phuongXa;
    @Column(nullable=false, length=50) private String quanHuyen;

    public DiaChi(Integer idDiaChi, String duong, String phuongXa, String quanHuyen) {
        this.idDiaChi = idDiaChi;
        this.duong = duong;
        this.phuongXa = phuongXa;
        this.quanHuyen = quanHuyen;
    }

    public DiaChi() {

    }

    public String getDuong() {
        return duong;
    }

    public void setDuong(String duong) {
        this.duong = duong;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public Integer getIdDiaChi() {
        return idDiaChi;
    }

    public void setIdDiaChi(Integer idDiaChi) {
        this.idDiaChi = idDiaChi;
    }
}
