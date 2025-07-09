package com.example.demo.Controller;

import com.example.demo.Service.DiemChuanCthService;
import com.example.demo.entity.DiemChuanCth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/diemchuan-cth")

public class DiemChuanCthController {
    private final DiemChuanCthService service;

    public DiemChuanCthController(DiemChuanCthService service) {
        this.service = service;
    }

    @GetMapping
    public List<DiemChuanCth> all() { return service.findAll(); }

    @GetMapping("/{id}") 
    public ResponseEntity<DiemChuanCth> one(@PathVariable Integer id) {
        return service.findOne(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DiemChuanCth create(@RequestBody DiemChuanCth dc) {
        return service.save(dc);
    }

    @PutMapping("/{id}")
    public DiemChuanCth update(@PathVariable Integer id, @RequestBody DiemChuanCth dc) {
        dc.setId(id);
        return service.save(dc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
