package com.example.demo.Service;

import com.example.demo.Repository.DiemChuanRepo;
import com.example.demo.Repository.TruongRepo;
import com.example.demo.entity.DiemChuan;
import com.example.demo.entity.DiemChuanKey;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiemChuanService {
    private final DiemChuanRepo repo;
    private final TruongRepo truongRepo;

    public DiemChuanService(DiemChuanRepo repo, TruongRepo truongRepo) {
        this.repo = repo;
        this.truongRepo = truongRepo;
    }

    public DiemChuan save(DiemChuan dc) {
        dc.setTruong(truongRepo.getReferenceById(dc.getId().getMaTruong()));
        return repo.save(dc);
    }

    public void delete(DiemChuanKey key) {
        repo.deleteById(key);
    }

    public List<DiemChuan> findByTruong(String maTruong) {
        return repo.findAllById_MaTruong(maTruong);
    }

    public Optional<DiemChuan> findOne(DiemChuanKey key) {
        return repo.findById(key);
    }

    public List<DiemChuan> findAll() {
        return repo.findAll();
    }
}
