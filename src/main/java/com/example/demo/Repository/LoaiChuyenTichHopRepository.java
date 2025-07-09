package com.example.demo.Repository;

import com.example.demo.entity.LoaiChuyenTichHop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface  LoaiChuyenTichHopRepository extends JpaRepository<LoaiChuyenTichHop, Byte> {

        Optional<LoaiChuyenTichHop> findByTenLoaiIgnoreCase(String tenLoai);


        List<LoaiChuyenTichHop> findByTenLoaiIn(List<String> tenLoais);


        @Query(value = "SELECT l FROM LoaiChuyenTichHop l ORDER BY l.id ASC LIMIT 10")
        List<LoaiChuyenTichHop> topTen();
    }