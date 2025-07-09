package com.example.demo.Specification;

import com.example.demo.entity.Truong;
import org.springframework.data.jpa.domain.Specification;

public class TruongSpecs {

    public static Specification<Truong> hasKeyword(String kw) {
        return (root, q, cb) -> {
            if (kw == null || kw.isBlank()) return null;
            String pattern = "%" + kw.trim().toLowerCase() + "%";
            return cb.or(
                cb.like(cb.lower(root.get("tenTruong")), pattern),
                cb.like(cb.lower(root.get("maTruong")), pattern)
            );
        };
    }

    public static Specification<Truong> inDistrict(String dist) {
        return (root, q, cb) -> {
            if (dist == null || dist.isBlank()) return null;
            return cb.equal(
               cb.lower(root.join("diaChi").get("quanHuyen")), dist.trim().toLowerCase()
            );
        };
    }
}
