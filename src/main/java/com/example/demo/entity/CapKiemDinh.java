package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "capkiemdinh")
@Data @NoArgsConstructor @AllArgsConstructor
public class CapKiemDinh {

    @Id
    @Column(name = "IdCap")
    private Byte idCap;

    @Column(name = "MoTaCap", length = 50, nullable = false)
    private String moTaCap;

    public String getMoTaCap() {
        return moTaCap;
    }

    public void setMoTaCap(String moTaCap) {
        this.moTaCap = moTaCap;
    }

    public Byte getIdCap() {
        return idCap;
    }

    public void setIdCap(Byte idCap) {
        this.idCap = idCap;
    }
}
