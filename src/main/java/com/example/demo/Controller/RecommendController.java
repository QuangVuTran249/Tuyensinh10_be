package com.example.demo.Controller;

import com.example.demo.DTO.RecommendDTO;
import com.example.demo.DTO.TripleRecommendDTO;
import com.example.demo.Service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/recommend")

@CrossOrigin(origins = "http://localhost:4200")   // cho phép Angular dev server
public class RecommendController {

    private final RecommendService service;

    public RecommendController(RecommendService service) {
        this.service = service;
    }

//    @GetMapping("/recommend")
//    public List<RecommendDTO> recommend(
//            @RequestParam BigDecimal score,
//            @RequestParam(required=false) Integer delta,
//            @RequestParam(required=false) Short   year,
//            @RequestParam(required=false) Integer limit) {
//
//        return service.recommend(score, delta, year, limit);
//    }
    @GetMapping("/triple")
    public List<TripleRecommendDTO> recommendTriple(
            @RequestParam BigDecimal score,
            @RequestParam(required = false) Integer delta,
            @RequestParam(required = false) Short year,
            @RequestParam(required = false) Integer limit
    ) {
        return service.recommendTriple(score, delta, year, limit);
    }
 

}
