package com.example.demo.DTO;

import lombok.*;
import com.example.demo.entity.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TruongDto {

    private String maTruong;
    private String tenTruong;
    private Short namThanhLap;
    private String website;
    private String dienThoai;
    private DiaChiDTO diaChi;
    private String capKiemDinh;
    private Set<String> features;
    private List<DiemChuanDTO> diemChuans;

    // 🎯 Phân loại ngoại ngữ
    private Set<String> ngoaiNguChinhKhoa;
    private Set<String> ngoaiNguTangCuong;

    public static TruongDto toDto(Truong t){
        // 1. Địa chỉ
        DiaChiDTO dc = new DiaChiDTO(
                t.getDiaChi().getDuong(),
                t.getDiaChi().getPhuongXa(),
                t.getDiaChi().getQuanHuyen()
        );

        // 2. Tiện ích
        Set<String> features = t.getFeatures().stream()
                .map(Feature::getTenFeature)
                .collect(Collectors.toSet());

        // 3. Ngoại ngữ (2 loại)
        Set<String> chinhKhoa = new HashSet<>();
        Set<String> tangCuong = new HashSet<>();

        if (t.getTruongNgoaiNgu() != null && t.getNgoaiNgus() != null) {
            for (TruongNgoaiNgu tnn : t.getTruongNgoaiNgu()) {
                Byte loai = tnn.getId().getLoaiNN();
                Byte idNN = tnn.getId().getIdNN();

                // Tìm tên ngoại ngữ tương ứng idNN
                t.getNgoaiNgus().stream()
                        .filter(n -> n.getIdNN().equals(idNN))
                        .findFirst()
                        .ifPresent(nn -> {
                            if (loai == 1) chinhKhoa.add(nn.getTenNN());
                            else if (loai == 2) tangCuong.add(nn.getTenNN());
                        });
            }
        }
        List<DiemChuanDTO> diemList = t.getDiemChuans().stream()
                .map(diem -> new DiemChuanDTO(
                        (short) diem.getId().getNam(),
                        diem.getDiemNV1(),
                        diem.getDiemNV2(),
                        diem.getDiemNV3(),
                        diem.getChiTieu(),
                        diem.getTyLeChoi()))
                .sorted(Comparator.comparing(DiemChuanDTO::nam).reversed())
                .toList();


        // 4. Build DTO
        TruongDto dto = new TruongDto();
        dto.setDiemChuans(diemList);
        dto.setMaTruong(t.getMaTruong());
        dto.setTenTruong(t.getTenTruong());
        dto.setNamThanhLap(t.getNamThanhLap());
        dto.setWebsite(t.getWebsite());
        dto.setDienThoai(t.getDienThoai());
        dto.setDiaChi(dc);
        dto.setCapKiemDinh(t.getCapKiemDinh().getMoTaCap());
        dto.setFeatures(features);
        dto.setNgoaiNguChinhKhoa(chinhKhoa);
        dto.setNgoaiNguTangCuong(tangCuong);

        return dto;
    }

    public String getMaTruong() {
        return maTruong;
    }

    public List<DiemChuanDTO> getDiemChuans() {
        return diemChuans;
    }

    public void setDiemChuans(List<DiemChuanDTO> diemChuans) {
        this.diemChuans = diemChuans;
    }

    public void setMaTruong(String maTruong) {
        this.maTruong = maTruong;
    }

    public String getTenTruong() {
        return tenTruong;
    }

    public void setTenTruong(String tenTruong) {
        this.tenTruong = tenTruong;
    }

    public Short getNamThanhLap() {
        return namThanhLap;
    }

    public void setNamThanhLap(Short namThanhLap) {
        this.namThanhLap = namThanhLap;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public DiaChiDTO getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChiDTO diaChi) {
        this.diaChi = diaChi;
    }

    public String getCapKiemDinh() {
        return capKiemDinh;
    }

    public void setCapKiemDinh(String capKiemDinh) {
        this.capKiemDinh = capKiemDinh;
    }

    public Set<String> getFeatures() {
        return features;
    }

    public void setFeatures(Set<String> features) {
        this.features = features;
    }

    public Set<String> getNgoaiNguChinhKhoa() {
        return ngoaiNguChinhKhoa;
    }

    public void setNgoaiNguChinhKhoa(Set<String> ngoaiNguChinhKhoa) {
        this.ngoaiNguChinhKhoa = ngoaiNguChinhKhoa;
    }

    public Set<String> getNgoaiNguTangCuong() {
        return ngoaiNguTangCuong;
    }

    public void setNgoaiNguTangCuong(Set<String> ngoaiNguTangCuong) {
        this.ngoaiNguTangCuong = ngoaiNguTangCuong;
    }
}
