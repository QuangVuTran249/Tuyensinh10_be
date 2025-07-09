package com.example.demo.Repository;

import com.example.demo.entity.TruongCSVatChat;
import com.example.demo.entity.TruongCSVatChatKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "truong-csvc")
public interface TruongCSVatChatRepo extends JpaRepository<TruongCSVatChat, TruongCSVatChatKey> {
    @Query("SELECT c FROM TruongCSVatChat c JOIN FETCH c.feature WHERE c.truong.maTruong = :maTruong")
    List<TruongCSVatChat> findByTruong_MaTruongFetchFeature(@Param("maTruong") String maTruong);

}
