package com.example.demo.Controller;

import com.example.demo.Service.DiemChuanService;
import com.example.demo.entity.DiemChuan;
import com.example.demo.entity.DiemChuanKey;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/diemchuan")

public class DiemChuanController {
    private final DiemChuanService service;

    public DiemChuanController(DiemChuanService service) {
        this.service = service;
    }

    @GetMapping
    public List<DiemChuan> all() { return service.findAll(); }

    @GetMapping("/{ma}/{nam}")
    public ResponseEntity<DiemChuan> one(@PathVariable String ma, @PathVariable int nam) {
        return service.findOne(new DiemChuanKey(ma, nam))
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DiemChuan create(@RequestBody DiemChuan dc) {
        return service.save(dc);
    }

    @PutMapping("/{ma}/{nam}")
    public DiemChuan update(@PathVariable String ma, @PathVariable int nam, @RequestBody DiemChuan dc) {
        dc.setId(new DiemChuanKey(ma, nam));
        return service.save(dc);
    }

    @DeleteMapping("/{ma}/{nam}")
    public void delete(@PathVariable String ma, @PathVariable int nam) {
        service.delete(new DiemChuanKey(ma, nam));
    }
}
