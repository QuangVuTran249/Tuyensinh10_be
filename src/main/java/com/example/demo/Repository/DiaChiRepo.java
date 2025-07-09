package com.example.demo.Repository;

import com.example.demo.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "dia-chi")
public interface DiaChiRepo extends JpaRepository<DiaChi, Integer> {
    Optional<DiaChi> findByDuongAndPhuongXaAndQuanHuyen(String duong, String phuongXa, String quanHuyen);
}
