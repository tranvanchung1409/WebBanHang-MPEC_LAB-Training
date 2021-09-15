package com.mpec.hanghoathongso.controller;

import com.mpec.hanghoathongso.entities.ThuongHieu;
import com.mpec.hanghoathongso.entities.entitiesDTO.ThuongHieuDTO;
import com.mpec.hanghoathongso.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/thuong-hieu")
public class ThuongHieuController {

    @Autowired
    ThuongHieuService thuongHieuService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThuongHieu> thuongHieus = thuongHieuService.findAll(pageable);
        return ResponseEntity.ok(thuongHieus);
    }


    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id",required = false) Integer id) {
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuService.findById(id);
        if (thuongHieuOptional.isPresent()) {
            return ResponseEntity.ok(thuongHieuOptional.get());
        } else {
            return ResponseEntity.ok("Thuong hieu khong ton tai");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text", required = false, defaultValue = "") String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThuongHieu> thuongHieus = thuongHieuService.search(text, pageable);
        return ResponseEntity.ok(thuongHieus);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ThuongHieuDTO thuongHieu) {
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuService.save(thuongHieu);
        if (thuongHieuOptional.isPresent()) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.ok("Failed");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ThuongHieuDTO thuongHieu) {
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuService.update(thuongHieu);
        if (thuongHieuOptional.isPresent()) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.ok("Failed");
        }
    }

    @DeleteMapping("/xoa")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        return thuongHieuService.delete(id) ? ResponseEntity.ok("Success") : ResponseEntity.ok("Failed");
    }
}
