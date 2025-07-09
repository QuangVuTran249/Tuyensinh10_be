/* repo/TruongRepoCustom.java */
package com.example.demo.Repository;

import com.example.demo.entity.Truong;

import java.util.Optional;

public interface TruongRepoCustom {
    Optional<Truong> findFullByMaTruong(String maTruong);
}
