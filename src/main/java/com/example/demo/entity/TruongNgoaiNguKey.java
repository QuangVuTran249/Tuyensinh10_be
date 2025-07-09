package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TruongNgoaiNguKey implements java.io.Serializable {
    @Column(name = "MaTruong", length = 8)
    private String maTruong;

    @Column(name = "IdNN")
    private Byte idNN;

    @Column(name = "LoaiNN")
    private Byte loaiNN;

    public String getMaTruong() {
        return maTruong;
    }

    public void setMaTruong(String maTruong) {
        this.maTruong = maTruong;
    }

    public Byte getIdNN() {
        return idNN;
    }

    public void setIdNN(Byte idNN) {
        this.idNN = idNN;
    }

    public Byte getLoaiNN() {
        return loaiNN;
    }

    public void setLoaiNN(Byte loaiNN) {
        this.loaiNN = loaiNN;
    }
}