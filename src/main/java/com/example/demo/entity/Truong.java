package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity @Table(name="truong")
 @NoArgsConstructor @AllArgsConstructor
public class Truong {
    @Id@Column(name="MaTruong", length=15) private String maTruong;

    @Column(nullable=false, length=120)private String tenTruong;

    @ManyToOne(fetch=FetchType.EAGER ) @JoinColumn(name="IdDiaChi", nullable=false)
    private DiaChi diaChi;

    @ManyToOne(fetch=FetchType.EAGER ) @JoinColumn(name="IdCap", nullable=false)
    private CapKiemDinh capKiemDinh;
    @OneToMany(mappedBy = "truong", fetch = FetchType.EAGER)
    private Set<DiemChuanCth> diemChuanCths = new HashSet<>();

    private Short  namThanhLap;      // YEAR → Short is enough
    @Column(length=150)  private String website;
    @Column(length=20)   private String dienThoai;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "truong_cosovatchat",
            joinColumns = @JoinColumn(name = "MaTruong"),
            inverseJoinColumns = @JoinColumn(name = "IdFeature"))
    private Set<Feature> features;
    @OneToMany(mappedBy = "truong", fetch = FetchType.EAGER)
    private Set<DiemChuan> diemChuans = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "truong_ngoaingu",
            joinColumns = @JoinColumn(name = "MaTruong"),
            inverseJoinColumns = @JoinColumn(name = "IdNN")
    )
    private Set<NgoaiNgu> ngoaiNgus;
// Getter and Setter for each field
@OneToMany(mappedBy = "truong", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
private Set<TruongNgoaiNgu> truongNgoaiNgu = new HashSet<>();

    public Set<DiemChuanCth> getDiemChuanCths() {
        return diemChuanCths;
    }

    public void setDiemChuanCths(Set<DiemChuanCth> diemChuanCths) {
        this.diemChuanCths = diemChuanCths;
    }

    public Set<TruongNgoaiNgu> getTruongNgoaiNgu() {
        return truongNgoaiNgu;
    }

    public void setTruongNgoaiNgu(Set<TruongNgoaiNgu> truongNgoaiNgu) {
        this.truongNgoaiNgu = truongNgoaiNgu;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Set<DiemChuan> getDiemChuans() {
        return diemChuans;
    }

    public void setDiemChuans(Set<DiemChuan> diemChuans) {
        this.diemChuans = diemChuans;
    }

    public Set<NgoaiNgu> getNgoaiNgus() {
        return ngoaiNgus;
    }

    public void setNgoaiNgus(Set<NgoaiNgu> ngoaiNgus) {
        this.ngoaiNgus = ngoaiNgus;
    }

    // Getter and Setter for `maTruong`
    public String getMaTruong() {
        return maTruong;
    }

    public void setMaTruong(String maTruong) {
        this.maTruong = maTruong;
    }

    // Getter and Setter for `tenTruong`
    public String getTenTruong() {
        return tenTruong;
    }

    public void setTenTruong(String tenTruong) {
        this.tenTruong = tenTruong;
    }

    // Getter and Setter for `diaChi`
    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }

    // Getter and Setter for `capKiemDinh`
    public CapKiemDinh getCapKiemDinh() {
        return capKiemDinh;
    }

    public void setCapKiemDinh(CapKiemDinh capKiemDinh) {
        this.capKiemDinh = capKiemDinh;
    }

    // Getter and Setter for `namThanhLap`
    public Short getNamThanhLap() {
        return namThanhLap;
    }

    public void setNamThanhLap(Short namThanhLap) {
        this.namThanhLap = namThanhLap;
    }

    // Getter and Setter for `website`
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    // Getter and Setter for `dienThoai`
    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
}
