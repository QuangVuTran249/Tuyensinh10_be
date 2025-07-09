package com.example.demo.Service;

import com.example.demo.DTO.RecommendDTO;
import com.example.demo.DTO.Stat;
import com.example.demo.DTO.TripleRecommendDTO;
import com.example.demo.entity.DiemChuan;
import com.example.demo.Repository.DiemChuanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendService {

    private final DiemChuanRepo repo;

    public RecommendService(DiemChuanRepo repo) {
        this.repo = repo;
    }

    private List<Short> latestThreeYears() {
        return repo.findAllYearsDesc().stream().limit(3).toList();
    }

    /**
     * @param score tổng điểm HS
     * @param delta khoảng +điểm an toàn (mặc định 2)
     * @param year  năm tra cứu (0/null ➜ max)
     * @param limit số record tối đa (null ➜ không cắt)
     */

    public List<TripleRecommendDTO> recommendTriple(BigDecimal score,
                                                    Integer      delta,
                                                    Short        year,
                                                    Integer      limit) {

        /* ---------- 0. Giới hạn tối đa kết quả ---------- */
        final int MAX_LIMIT = 20;
        int effectiveLimit = (limit == null || limit <= 0 || limit > MAX_LIMIT)
                ? MAX_LIMIT
                : limit;

        /* ---------- 1. Xác định năm cần sử dụng ---------- */
        List<Short> years = (year != null) ? List.of(year) : latestThreeYears();

        /* ---------- 2. Đọc dữ liệu & tính thống kê ---------- */
        List<DiemChuan> all = repo.findAllInYears(years);

        Map<String, Stat> stats = new HashMap<>();
        all.stream()
                .collect(Collectors.groupingBy(dc -> dc.getTruong().getMaTruong()))
                .forEach((ma, list) -> {
                    List<BigDecimal> nv1s = list.stream()
                            .map(DiemChuan::getDiemNV1)
                            .filter(Objects::nonNull)
                            .sorted()
                            .toList();
                    if (nv1s.size() < 2) return;                 // bỏ trường ít dữ liệu

                    BigDecimal median = nv1s.get(nv1s.size() / 2);
                    double avg       = nv1s.stream()
                            .mapToDouble(BigDecimal::doubleValue)
                            .average().orElse(0);
                    double variance  = nv1s.stream()
                            .mapToDouble(BigDecimal::doubleValue)
                            .map(v -> Math.pow(v - avg, 2))
                            .average().orElse(0);

                    stats.put(ma, new Stat(median, Math.sqrt(variance)));
                });

        /* ---------- 3. Lọc bản ghi của năm mới nhất ---------- */
        short currentYear = years.get(0);  // years giảm dần
        List<DiemChuan> current = all.stream()
                .filter(dc -> dc.getId().getNam() == currentYear)
                .filter(dc -> stats.containsKey(dc.getTruong().getMaTruong()))
                .toList();

        /* ---------- 4. Khoảng điểm cho phép ---------- */
        int d = (delta == null || delta <= 0) ? 2 : delta;         // mặc định 2
        BigDecimal min = score.subtract(BigDecimal.valueOf(d));
        BigDecimal max = score;

        /* ---------- 5. Pool & sắp xếp (median ↓, tyLeChoi ↑) ---------- */
        List<DiemChuan> pool = current.stream()
                .filter(dc -> {
                    BigDecimal m = stats.get(dc.getTruong().getMaTruong()).median();
                    return m.compareTo(min) >= 0 && m.compareTo(max) <= 0;
                })
                .sorted(
                        Comparator.comparing(
                                        (DiemChuan dc) -> stats.get(dc.getTruong().getMaTruong()).median())
                                .reversed()                         // median cao → trước
                                .thenComparing(DiemChuan::getTyLeChoi,
                                        Comparator.nullsLast(Comparator.naturalOrder()))
                )
                .toList();

        /* ---------- 6. Tổ hợp 3 trường với biên chênh lệch ---------- */
        final BigDecimal MIN12 = new BigDecimal("0.5");
        final BigDecimal MAX12 = new BigDecimal("1.0");
        final BigDecimal MIN13 = new BigDecimal("1.0");
        final BigDecimal MAX13 = new BigDecimal("1.5");

        List<TripleRecommendDTO> result = new ArrayList<>();

        for (int i = 0; i < pool.size(); i++) {
            DiemChuan d1 = pool.get(i);
            BigDecimal m1 = stats.get(d1.getTruong().getMaTruong()).median();

            for (int j = i + 1; j < pool.size(); j++) {
                DiemChuan d2 = pool.get(j);
                BigDecimal m2 = stats.get(d2.getTruong().getMaTruong()).median();

                BigDecimal diff12 = m1.subtract(m2);
                if (diff12.compareTo(MIN12) < 0 || diff12.compareTo(MAX12) > 0) continue; // NV2 sai biên

                for (int k = j + 1; k < pool.size(); k++) {
                    DiemChuan d3 = pool.get(k);
                    BigDecimal m3 = stats.get(d3.getTruong().getMaTruong()).median();

                    if (m2.compareTo(m3) <= 0) continue;  // phải m1 > m2 > m3

                    BigDecimal diff13 = m1.subtract(m3);
                    if (diff13.compareTo(MIN13) < 0 || diff13.compareTo(MAX13) > 0) continue; // NV3 sai biên

                    // Tạo combo hợp lệ
                    result.add(TripleRecommendDTO.of(
                            d1, d2, d3,
                            stats.get(d1.getTruong().getMaTruong()),
                            stats.get(d2.getTruong().getMaTruong()),
                            stats.get(d3.getTruong().getMaTruong()),
                            score));

                    if (result.size() >= effectiveLimit) return result;  // đã đủ 20
                }
            }
        }

        return result;
    }


//    public List<TripleRecommendDTO> recommendTriple(BigDecimal score, Integer delta, Short year, Integer limit) {
//        List<Short> years = (year != null) ? List.of(year) : latestThreeYears();
//        List<DiemChuan> all = repo.findAllInYears(years);
//
//        Map<String, List<DiemChuan>> grouped = all.stream()
//                .collect(Collectors.groupingBy(dc -> dc.getTruong().getMaTruong()));
//
//        Map<String, Stat> stats = new HashMap<>();
//        grouped.forEach((ma, list) -> {
//            List<BigDecimal> nv1s = list.stream().map(DiemChuan::getDiemNV1).filter(Objects::nonNull).sorted().toList();
//            if (nv1s.size() < 2) return;
//            BigDecimal median = nv1s.get(nv1s.size() / 2);
//            double avg = nv1s.stream().mapToDouble(BigDecimal::doubleValue).average().orElse(0);
//            double variance = nv1s.stream().mapToDouble(BigDecimal::doubleValue)
//                    .map(x -> Math.pow(x - avg, 2)).average().orElse(0);
//            stats.put(ma, new Stat(median, Math.sqrt(variance)));
//        });
//
//        // Lấy bản ghi năm mới nhất (hiện tại)
//        short currentYear = years.get(0);
//        List<DiemChuan> current = all.stream()
//                .filter(dc -> dc.getId().getNam() == currentYear)
//                .filter(dc -> stats.containsKey(dc.getTruong().getMaTruong()))
//                .toList();
//
//        int d = (delta != null && delta > 0) ? delta : 0;
//        BigDecimal min = score.subtract(BigDecimal.valueOf(d));
//        BigDecimal max = score;
//
//        List<DiemChuan> pool = current.stream()
//                .filter(dc -> {
//                    BigDecimal median = stats.get(dc.getTruong().getMaTruong()).median();
//                    return median.compareTo(min) >= 0 && median.compareTo(max) <= 0;
//                })
//                .sorted(Comparator.comparing(dc -> dc.getTyLeChoi(),
//                        Comparator.nullsLast(Comparator.naturalOrder())))
//                .toList();
//
//        // Ghép thành các bộ 3 combo [nv1, nv2, nv3]
//        List<TripleRecommendDTO> result = new ArrayList<>();
//        for (int i = 0; i + 2 < pool.size(); i++) {
//            DiemChuan d1 = pool.get(i), d2 = pool.get(i + 1), d3 = pool.get(i + 2);
//            Stat s1 = stats.get(d1.getTruong().getMaTruong());
//            Stat s2 = stats.get(d2.getTruong().getMaTruong());
//            Stat s3 = stats.get(d3.getTruong().getMaTruong());
//
//            if (s1.median().compareTo(s2.median()) <= 0 && s2.median().compareTo(s3.median()) <= 0) {
//                result.add(TripleRecommendDTO.of(d1, d2, d3, s1, s2, s3, score));
//                if (limit != null && result.size() >= limit) break;
//            }
//        }
//
//        return result;
//    }
//
}