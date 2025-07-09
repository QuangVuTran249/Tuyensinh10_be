package com.example.demo.Repository;

import com.example.demo.entity.DiemChuan;
import com.example.demo.entity.DiemChuanKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DiemChuanRepo
        extends JpaRepository<DiemChuan, DiemChuanKey> {
    List<DiemChuan> findAllById_MaTruong(String maTruong);
    @Query("SELECT DISTINCT dc.id.nam FROM DiemChuan dc ORDER BY dc.id.nam DESC")
    List<Short> findAllYearsDesc();  // dùng trong service để lấy top 3

    @Query("""
    SELECT dc FROM DiemChuan dc
     JOIN FETCH dc.truong
     WHERE dc.id.nam IN :years
""")
    List<DiemChuan> findAllInYears(@Param("years") List<Short> years);

    /* Năm mới nhất có dữ liệu */
    @Query("SELECT MAX(dc.id.nam) FROM DiemChuan dc")
    Short findMaxYear();

    /* Trường có điểm NV1/2/3 nằm trong [min, max] của năm chỉ định */
    @Query("""
       SELECT dc FROM DiemChuan dc
        JOIN FETCH dc.truong
       WHERE dc.id.nam = :year
         AND (
              (dc.diemNV1 BETWEEN :min AND :max)
           OR (dc.diemNV2 BETWEEN :min AND :max)
           OR (dc.diemNV3 BETWEEN :min AND :max)
         )
    """)
    List<DiemChuan> findCandidates(
            @Param("year") short year,
            @Param("min")  BigDecimal min,
            @Param("max")  BigDecimal max);

    /* (Tuỳ chọn) Xem 1 trường theo năm */
    Optional<DiemChuan> findById_MaTruongAndId_Nam(String maTruong, short nam);
}
