package com.example.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor
@AllArgsConstructor
public class TruongCSVatChatKey implements Serializable {
    private String maTruong;
    private Byte   idFeature;

    public String getMaTruong() {
        return maTruong;
    }

    public void setMaTruong(String maTruong) {
        this.maTruong = maTruong;
    }

    public Byte getIdFeature() {
        return idFeature;
    }

    public void setIdFeature(Byte idFeature) {
        this.idFeature = idFeature;
    }
}