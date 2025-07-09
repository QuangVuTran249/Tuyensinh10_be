package com.example.demo.Repository;

import com.example.demo.entity.TruongTuyenSinh;
import com.example.demo.entity.TruongTuyenSinhKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "truong-tuyen-sinh")
public interface TruongTuyenSinhRepo extends JpaRepository<TruongTuyenSinh, TruongTuyenSinhKey> {
}
