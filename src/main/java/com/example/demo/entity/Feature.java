package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name="feature")
@Data @NoArgsConstructor @AllArgsConstructor
public class Feature {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IdFeature")
    private Byte  idFeature;
    @Column(nullable=false, length=40)
    private String tenFeature;

    public Byte getIdFeature() {
        return idFeature;
    }

    public void setIdFeature(Byte idFeature) {
        this.idFeature = idFeature;
    }

    public String getTenFeature() {
        return tenFeature;
    }

    public void setTenFeature(String tenFeature) {
        this.tenFeature = tenFeature;
    }
}

