package com.example.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable @Data @NoArgsConstructor @AllArgsConstructor
public class TruongTuyenSinhKey implements Serializable {
    private String maTruong;
    private Byte   idHinhThuc;
}
