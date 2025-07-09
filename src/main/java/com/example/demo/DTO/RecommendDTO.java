package com.example.demo.DTO;

import com.example.demo.entity.DiemChuan;

import java.math.BigDecimal;
public record RecommendDTO(
        String     maTruong,
        String     tenTruong,
        BigDecimal diemNV1,
        BigDecimal diemNV2,
        BigDecimal diemNV3,
        BigDecimal tyLeChoi,
        BigDecimal median3Yr,//Trung bình (mean):
        double     sigma3Yr, 
        double     probability,
        int        suggestedNv
) {
    public RecommendDTO(DiemChuan dc, BigDecimal median, double sigma, BigDecimal userScore, int nv) {
        this(
                dc.getTruong().getMaTruong(),
                dc.getTruong().getTenTruong(),
                dc.getDiemNV1(),
                dc.getDiemNV2(),
                dc.getDiemNV3(),
                dc.getTyLeChoi(),
                median,
                sigma,
                computeProbability(median, sigma, userScore),
                nv
        );
    }

    private static double computeProbability(BigDecimal median, double sigma, BigDecimal userScore) {
        if (sigma <= 0) return 0.5;  // tránh chia 0
        double z = (median.doubleValue() - userScore.doubleValue()) / sigma;
        System.out.printf("μ=%s σ=%.3f score=%s z=%.3f p=%.3f%n",
                median, sigma, userScore, z, 1 - cdf(z));
        return round(1 - cdf(z)); // P(x > cutoff)
    }

    private static double cdf(double z) {
        return 0.5 * (1 + erf(z / Math.sqrt(2)));
    }

    private static double erf(double x) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(x));
        double tau = t * Math.exp(-x * x
                - 1.26551223 + t * (1.00002368 + t * (0.37409196 +
                t * (0.09678418 + t * (-0.18628806 + t * (0.27886807 +
                        t * (-1.13520398 + t * (1.48851587 + t * (-0.82215223 +
                                t * 0.17087277)))))))));
        return x >= 0 ? 1 - tau : tau - 1;
    }

    private static double round(double d) {
        return Math.round(d * 1000.0) / 1000.0;
    }
}
