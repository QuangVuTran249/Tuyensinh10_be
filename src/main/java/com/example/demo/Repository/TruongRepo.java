package com.example.demo.Repository;

import com.example.demo.entity.Truong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "truong")
public interface TruongRepo extends JpaRepository<Truong, String>, JpaSpecificationExecutor<Truong> {

}
