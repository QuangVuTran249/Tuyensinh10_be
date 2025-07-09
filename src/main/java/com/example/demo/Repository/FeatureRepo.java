package com.example.demo.Repository;

import com.example.demo.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "feature")
public interface FeatureRepo extends JpaRepository<Feature, Byte> {
}
