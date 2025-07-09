package com.example.demo.Controller;

import com.example.demo.DTO.TruongDetailDTO;
import com.example.demo.DTO.TruongDto;
import com.example.demo.Repository.TruongRepo;
import com.example.demo.Service.TruongService;
import com.example.demo.Specification.TruongSpecs;
import com.example.demo.entity.Truong;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schools")
public class TruongController {
    private final TruongService truongService;
    private final TruongRepo truongRepo;


    public TruongController(TruongService truongService,TruongRepo truongRepo) {
        this.truongService = truongService;
        this.truongRepo = truongRepo;
    }

    @GetMapping("/{maTruong}")
    public ResponseEntity<TruongDto> getSchool(@PathVariable String maTruong) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(truongService.layChiTiet(maTruong));
    }
    @GetMapping
    public Page<TruongDto> search(
            @RequestParam(required=false) String q,
            @RequestParam(required=false) String district,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="20") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("tenTruong"));
        Specification<Truong> spec = Specification
                .where(TruongSpecs.hasKeyword(q))
                .and(TruongSpecs.inDistrict(district));

        return truongRepo.findAll(spec, pageable)
                .map(t -> TruongDto.toDto(t));
    }

}
