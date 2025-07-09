package com.example.demo.Service;

import com.example.demo.Repository.DiemChuanCthRepo;
import com.example.demo.Repository.LoaiChuyenTichHopRepository;
import com.example.demo.Repository.TruongRepo;
import com.example.demo.entity.DiemChuanCth;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiemChuanCthService {
    private final DiemChuanCthRepo repo;
    private final TruongRepo truongRepo;
    private final LoaiChuyenTichHopRepository loaiRepo;

    public DiemChuanCthService(DiemChuanCthRepo repo, TruongRepo truongRepo, LoaiChuyenTichHopRepository loaiRepo) {
        this.repo = repo;
        this.truongRepo = truongRepo;
        this.loaiRepo = loaiRepo;
    }

    public DiemChuanCth save(DiemChuanCth dc) {
        dc.setTruong(truongRepo.getReferenceById(dc.getTruong().getMaTruong()));
        dc.setLoai(loaiRepo.getReferenceById(dc.getLoai().getId()));
        return repo.save(dc);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<DiemChuanCth> findByTruong(String maTruong) {
        return repo.findAllByTruong_MaTruong(maTruong);
    }

    public Optional<DiemChuanCth> findOne(Integer id) {
        return repo.findById(id);
    }

    public List<DiemChuanCth> findAll() {
        return repo.findAll();
    }
}
