package com.example.demo.Controller;

import com.example.demo.DTO.SchoolAdminDto;
import com.example.demo.DTO.SchoolAdminRequest;
import com.example.demo.Service.SchoolAdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/schools")

public class SchoolAdminController {

    private final SchoolAdminService service;

    public SchoolAdminController(SchoolAdminService service) {
        this.service = service;
    }

    /* GET /api/admin/schools */
    @GetMapping
    public List<SchoolAdminDto> list() {
        return service.findAll();
    }

    /* GET /api/admin/schools/{ma} */
    @GetMapping("/{ma}")
    public SchoolAdminDto get(@PathVariable String ma) {
        return service.findOne(ma);
    }

    /* POST (create) */
    @PostMapping
    public ResponseEntity<SchoolAdminDto> create(@RequestBody @Valid SchoolAdminRequest rq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(rq));
    }

    /* PUT (update) */
    @PutMapping("/{ma}")
    public SchoolAdminDto update(@PathVariable String ma,
                                 @RequestBody @Valid SchoolAdminRequest rq) {
        return service.update(ma, rq);
    }

    /* DELETE */
    @DeleteMapping("/{ma}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String ma) {
        service.delete(ma);
    }
}
