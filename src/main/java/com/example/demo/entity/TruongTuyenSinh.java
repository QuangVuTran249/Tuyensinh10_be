package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="truong_tuyensinh")
@Data
@NoArgsConstructor @AllArgsConstructor
public class TruongTuyenSinh {
    @EmbeddedId   private TruongTuyenSinhKey id;

    @ManyToOne(fetch=FetchType.LAZY) @MapsId("maTruong")
    @JoinColumn(name="MaTruong")     private Truong truong;

    @ManyToOne(fetch=FetchType.LAZY) @MapsId("idHinhThuc")
    @JoinColumn(name="IdHinhThuc")   private HinhThucTuyenSinh hinhThuc;
}