package com.example.demo.Repository;

import com.example.demo.entity.NgoaiNgu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "ngoai-ngu")
public interface NgoaiNguRepo extends JpaRepository<NgoaiNgu, Byte> {
}
