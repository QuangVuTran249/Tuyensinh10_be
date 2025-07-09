package com.example.demo.Repository;

import com.example.demo.entity.HinhThucTuyenSinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "hinh-thuc-tuyen-sinh")
public interface HinhThucRepo extends JpaRepository<HinhThucTuyenSinh, Byte> {
}
