/* repo/impl/TruongRepoCustomImpl.java */
package com.example.demo.Repository;

import com.example.demo.entity.*;
import com.example.demo.Repository.TruongRepoCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TruongRepoCustomImpl implements TruongRepoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Truong> findFullByMaTruong(String maTruong) {

        // fetch join tới tất cả mối quan hệ cần thiết để tránh Lazy-Load
        var query = em.createQuery("""
            select distinct t
            from Truong t
            left join fetch t.diaChi dc
            left join fetch t.capKiemDinh ck
            left join fetch t.csVatChats csvc           -- mappedBy trong Truong
            left join fetch csvc.feature f
            left join fetch t.ngoaiNgus ng              -- mappedBy trong Truong
            left join fetch ng.ngoaiNgu n
            where t.maTruong = :ma
        """, Truong.class);

        query.setParameter("ma", maTruong);
        return query.getResultStream().findFirst();
    }
}
