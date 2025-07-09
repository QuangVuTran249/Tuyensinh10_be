package com.example.demo.Repository;

import com.example.demo.entity.TruongNgoaiNgu;
import com.example.demo.entity.TruongNgoaiNguKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruongNgoaiNguRepo extends JpaRepository<TruongNgoaiNgu, TruongNgoaiNguKey> {
    List<TruongNgoaiNgu> findById_MaTruong(String maTruong);
}
