package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name="truong_cosovatchat")
@Data @NoArgsConstructor @AllArgsConstructor
public class TruongCSVatChat {
    @EmbeddedId private TruongCSVatChatKey id;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("maTruong")
    @JoinColumn(name="MaTruong")
    private Truong truong;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("idFeature")
    @JoinColumn(name="IdFeature")
    private Feature feature;

    public TruongCSVatChatKey getId() {
        return id;
    }

    public void setId(TruongCSVatChatKey id) {
        this.id = id;
    }

    public Truong getTruong() {
        return truong;
    }

    public void setTruong(Truong truong) {
        this.truong = truong;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}