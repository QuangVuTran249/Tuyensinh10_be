package com.example.demo.Service;

import com.example.demo.DTO.DiaChiDTO;
import com.example.demo.DTO.DiemChuanDTO;
import com.example.demo.DTO.TruongDetailDTO;
import com.example.demo.DTO.TruongDto;
import com.example.demo.Repository.TruongRepo;
import com.example.demo.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

@Service
public class TruongService {
    private final TruongRepo truongRepo;

    public TruongService(TruongRepo truongRepo) {
        this.truongRepo = truongRepo;
    }
    @Transactional
    public TruongDto layChiTiet(String maTruong) throws ChangeSetPersister.NotFoundException {
        Truong t = truongRepo.findById(maTruong)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        return TruongDto.toDto(t);
    }

}
