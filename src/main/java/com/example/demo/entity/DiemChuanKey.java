package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class DiemChuanKey implements Serializable {
    @Column(name = "MaTruong", length = 15)
    private String maTruong;

    @Column(name = "Nam")
    private int nam;
    public DiemChuanKey() {
    }
    // Constructor cần thiết cho mã nguồn
    public DiemChuanKey(String maTruong, int nam) {
        this.maTruong = maTruong;
        this.nam = nam;
    }

    public String getMaTruong() {
        return maTruong;
    }

    public void setMaTruong(String maTruong) {
        this.maTruong = maTruong;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }
}
