package com.mpec.hanghoathongso.controller;

import com.mpec.hanghoathongso.entities.NhomHang;
import com.mpec.hanghoathongso.entities.NhomThongSo;
import com.mpec.hanghoathongso.entities.entitiesDTO.NhomHangDTO;
import com.mpec.hanghoathongso.entities.entitiesDTO.NhomThongSoDTO;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.NhomThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/nhom-thong-so")
public class NhomThongSoController {

    @Autowired
    NhomThongSoService nhomThongSoService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhomThongSo> nhomThongSoPage = nhomThongSoService.findAll(pageable);
        return ResponseEntity.ok(nhomThongSoPage);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id", required = false) Integer id) {
        Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoService.findById(id);
        if (nhomThongSoOptional.isPresent()) {
            return ResponseEntity.ok(nhomThongSoOptional.get());
        } else {
            return ResponseEntity.ok("Nhóm thông số không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text", defaultValue = "", required = false) String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhomThongSo> nhomThongSoPage = nhomThongSoService.search(text, pageable);
        return ResponseEntity.ok(nhomThongSoPage);
    }

    @GetMapping("/find-by-hang-hoa")
    public ResponseEntity<?> findByHangHoaId(@RequestParam(name = "hang-hoa-id", defaultValue = "", required = false) Integer hangHoaId,
                                             @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhomThongSo> nhomThongSoPage = nhomThongSoService.findByHangHoaId(hangHoaId, pageable);
        return ResponseEntity.ok(nhomThongSoPage);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody NhomThongSoDTO nhomThongSo) {
        MyResponse response = nhomThongSoService.save(nhomThongSo);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody NhomThongSoDTO nhomThongSo) {
        Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoService.update(nhomThongSo);
        if (nhomThongSoOptional.isPresent()) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.ok("Failed");
        }
    }

    @DeleteMapping("/xoa")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        return nhomThongSoService.delete(id) ? ResponseEntity.ok("Success") : ResponseEntity.ok("Failed");
    }
}
