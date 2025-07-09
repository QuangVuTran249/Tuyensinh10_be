package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* NgoaiNgu.java */
@Entity @Table(name="ngoaingu")
@Data @NoArgsConstructor @AllArgsConstructor
public class NgoaiNgu {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IdNN")
    private Byte  idNN;
    @Column(nullable=false, length=20)
    private String tenNN;

    public Byte getIdNN() {
        return idNN;
    }

    public void setIdNN(Byte idNN) {
        this.idNN = idNN;
    }

    public String getTenNN() {
        return tenNN;
    }

    public void setTenNN(String tenNN) {
        this.tenNN = tenNN;
    }
}