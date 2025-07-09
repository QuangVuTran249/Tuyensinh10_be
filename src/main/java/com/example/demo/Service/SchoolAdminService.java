package com.example.demo.Service;

import com.example.demo.DTO.SchoolAdminDto;
import com.example.demo.DTO.SchoolAdminRequest;
import com.example.demo.Repository.*;
import com.example.demo.entity.DiaChi;
import com.example.demo.entity.Feature;
import com.example.demo.entity.NgoaiNgu;
import com.example.demo.entity.Truong;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

@Transactional
public class SchoolAdminService {

    private final TruongRepo truongRepo;
    private final DiaChiRepo diaChiRepo;
    private final FeatureRepo featureRepo;
    private final NgoaiNguRepo ngoaiNguRepo;
    private final CapKiemDinhRepo capRepo;

    public SchoolAdminService(TruongRepo truongRepo, DiaChiRepo diaChiRepo, FeatureRepo featureRepo, NgoaiNguRepo ngoaiNguRepo, CapKiemDinhRepo capRepo) {
        this.truongRepo = truongRepo;
        this.diaChiRepo = diaChiRepo;
        this.featureRepo = featureRepo;
        this.ngoaiNguRepo = ngoaiNguRepo;
        this.capRepo = capRepo;
    }

    public SchoolAdminDto create(SchoolAdminRequest rq) {
        Truong t = mapToEntityC(new Truong(), rq);
        return SchoolAdminDto.of(truongRepo.save(t));
    }

    /* ───── UPDATE ───── */
    public SchoolAdminDto update(String maTruong, SchoolAdminRequest rq) {
        Truong t = truongRepo.findById(maTruong)
                   .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy "+maTruong));

        return SchoolAdminDto.of(truongRepo.save(mapToEntity(t, rq)));
    }

    /* ───── DELETE ───── */
    public void delete(String maTruong) {
        truongRepo.deleteById(maTruong);
    }

    /* ───── READ ───── */
    public List<SchoolAdminDto> findAll() {
        return truongRepo.findAll().stream().map(SchoolAdminDto::of).toList();
    }
    public SchoolAdminDto findOne(String ma) {
        return SchoolAdminDto.of(
                truongRepo.findById(ma)
                          .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy "+ma)));
    }

    /* ───── Helper mapping ───── */
    private Truong mapToEntity(Truong t, SchoolAdminRequest rq) {

        /* Địa chỉ – tái sử dụng nếu đã tồn tại */
        DiaChi dc = diaChiRepo.findByDuongAndPhuongXaAndQuanHuyen(
                rq.duong(), rq.phuongXa(), rq.quanHuyen())
                .orElseGet(() -> diaChiRepo.save(
                        new DiaChi(null, rq.duong(), rq.phuongXa(), rq.quanHuyen())));

        t.setTenTruong(rq.tenTruong());
        t.setNamThanhLap(rq.namThanhLap());
        t.setWebsite(rq.website());
        t.setDienThoai(rq.dienThoai());
        t.setDiaChi(dc);
        t.setCapKiemDinh(capRepo.getReferenceById(rq.idCap()));

        /* CSVC */
        Set<Byte> ids = rq.featureIds() == null ? Set.of() : rq.featureIds();
        Set<Feature> fs = new HashSet<>(featureRepo.findAllById(ids));
        t.setFeatures(fs);

        /* Ngoại ngữ 1 & 2 */
        Set<NgoaiNgu> nnSet = new HashSet<>();
        if (rq.idNN1() != null) nnSet.add(ngoaiNguRepo.getReferenceById(rq.idNN1()));
        if (rq.idNN2() != null) nnSet.add(ngoaiNguRepo.getReferenceById(rq.idNN2()));
        t.setNgoaiNgus(nnSet);

        return t;
    }
    private Truong mapToEntityC(Truong t, SchoolAdminRequest rq) {

        /* Địa chỉ – tái sử dụng nếu đã tồn tại */
        DiaChi dc = diaChiRepo.findByDuongAndPhuongXaAndQuanHuyen(
                        rq.duong(), rq.phuongXa(), rq.quanHuyen())
                .orElseGet(() -> diaChiRepo.save(
                        new DiaChi(null, rq.duong(), rq.phuongXa(), rq.quanHuyen())));

        t.setMaTruong(rq.maTruong());
        t.setTenTruong(rq.tenTruong());
        t.setNamThanhLap(rq.namThanhLap());
        t.setWebsite(rq.website());
        t.setDienThoai(rq.dienThoai());
        t.setDiaChi(dc);
        t.setCapKiemDinh(capRepo.getReferenceById(rq.idCap()));

        /* CSVC */
        Set<Byte> ids = rq.featureIds() == null ? Set.of() : rq.featureIds();
        Set<Feature> fs = new HashSet<>(featureRepo.findAllById(ids));
        t.setFeatures(fs);

        /* Ngoại ngữ 1 & 2 */
        Set<NgoaiNgu> nnSet = new HashSet<>();
        if (rq.idNN1() != null) nnSet.add(ngoaiNguRepo.getReferenceById(rq.idNN1()));
        if (rq.idNN2() != null) nnSet.add(ngoaiNguRepo.getReferenceById(rq.idNN2()));
        t.setNgoaiNgus(nnSet);

        return t;
    }
}
