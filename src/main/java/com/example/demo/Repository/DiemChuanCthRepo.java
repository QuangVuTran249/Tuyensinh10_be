package com.example.demo.Repository;

import com.example.demo.entity.DiemChuanCth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiemChuanCthRepo extends JpaRepository<DiemChuanCth, Integer> {
    List<DiemChuanCth> findAllByTruong_MaTruong(String maTruong);

}
