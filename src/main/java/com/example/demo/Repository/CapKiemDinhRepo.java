package com.example.demo.Repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="cap-kiem-dinh")
public interface CapKiemDinhRepo       extends JpaRepository<CapKiemDinh,Byte> {}

