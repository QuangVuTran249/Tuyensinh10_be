package com.example.demo.DTO;

import com.example.demo.entity.DiemChuan;
import com.example.demo.DTO.Stat;
import java.math.BigDecimal;

public record TripleRecommendDTO(
        RecommendDTO nv1,
        RecommendDTO nv2,
        RecommendDTO nv3
) {
    public static TripleRecommendDTO of(
            DiemChuan d1, DiemChuan d2, DiemChuan d3,
            Stat s1, Stat s2, Stat s3,
            BigDecimal score) {
        return new TripleRecommendDTO(
                new RecommendDTO(d1, s1.median(), s1.sigma(), score, 1),
                new RecommendDTO(d2, s2.median(), s2.sigma(), score, 2),
                new RecommendDTO(d3, s3.median(), s3.sigma(), score, 3)
        );
    }
}
