package com.mpec.hanghoathongso.controller;

import com.mpec.hanghoathongso.entities.NhomHang;
import com.mpec.hanghoathongso.entities.entitiesDTO.NhomHangDTO;
import com.mpec.hanghoathongso.service.NhomHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/nhom-hang")
public class NhomHangController {

    @Autowired
    NhomHangService nhomHangService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhomHang> nhomHangPage = nhomHangService.findAll(pageable);
        return ResponseEntity.ok(nhomHangPage);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id", required = false) Integer id) {
        Optional<NhomHang> nhomHangOptional = nhomHangService.findById(id);
        if (nhomHangOptional.isPresent()) {
            return ResponseEntity.ok(nhomHangOptional.get());
        } else {
            return ResponseEntity.ok("Thuong hieu khong ton tai");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text", defaultValue = "", required = false) String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhomHang> nhomHangs = nhomHangService.search(text, pageable);
        return ResponseEntity.ok(nhomHangs);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody NhomHangDTO nhomHang) {
        Optional<NhomHang> nhomHangOptional = nhomHangService.save(nhomHang);
        if (nhomHangOptional.isPresent()) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.ok("Failed");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody NhomHangDTO nhomHang) {
        Optional<NhomHang> nhomHangOptional = nhomHangService.update(nhomHang);
        if (nhomHangOptional.isPresent()) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.ok("Failed");
        }
    }

    @DeleteMapping("/xoa")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        return nhomHangService.delete(id) ? ResponseEntity.ok("Success") : ResponseEntity.ok("Failed");
    }
}
