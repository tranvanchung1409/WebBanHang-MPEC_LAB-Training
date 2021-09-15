package com.mpec.hanghoathongso.controller;

import com.mpec.hanghoathongso.entities.HangHoaThongSo;
import com.mpec.hanghoathongso.entities.entitiesDTO.HangHoaThongSoDTO;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.HangHoaThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hang-hoa-thong-so")
public class HangHoaThongSoController {

    @Autowired
    HangHoaThongSoService hangHoaThongSoService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findAll(pageable);
        return ResponseEntity.ok(hangHoaThongSos);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id", required = false) Integer id) {
        Optional<HangHoaThongSo> hangHoaThongSo = hangHoaThongSoService.findById(id);
        if (hangHoaThongSo.isPresent()) {
            return ResponseEntity.ok(hangHoaThongSo.get());
        } else {
            return ResponseEntity.ok("Thông số hàng hóa không tồn tại");
        }
    }

//    @GetMapping("/search")
//    public ResponseEntity<?> search(@RequestParam(name = "text", defaultValue = "", required = false) String text,
//                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
//                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<HangHoaThongSo> hangHoaThongSoPage = hangHoaThongSoService.search(text, pageable);
//        return ResponseEntity.ok(hangHoaThongSoPage);
//    }

    @GetMapping("/find-by-hang-hoa")
    public ResponseEntity<?> findByHangHoaId(@RequestParam(name = "hang-hoa-id", defaultValue = "", required = false) Integer hangHoaId,
                                             @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findByHangHoaId(hangHoaId, pageable);
        return ResponseEntity.ok(hangHoaThongSos);
    }

    @GetMapping("/find-by-thong-so-ki-thuat")
    public ResponseEntity<?> findByThongSoKiThuatId(@RequestParam(name = "thong-so-ki-thuat-id", defaultValue = "", required = false) Integer thongSoKiThuatId,
                                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findByThongSoKiThuatId(thongSoKiThuatId, pageable);
        return ResponseEntity.ok(hangHoaThongSos);
    }

    @GetMapping("/find-by-thong-so-chi-tiet")
    public ResponseEntity<?> findByThongSoChiTietId(@RequestParam(name = "hang-hoa-id", defaultValue = "", required = false) Integer thongSoChiTietId,
                                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findByThongSoChiTietId(thongSoChiTietId, pageable);
        return ResponseEntity.ok(hangHoaThongSos);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HangHoaThongSoDTO hangHoaThongSo) {
        MyResponse response = hangHoaThongSoService.save(hangHoaThongSo);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody HangHoaThongSoDTO hangHoaThongSo) {
        MyResponse response = hangHoaThongSoService.update(hangHoaThongSo);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/xoa")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        return hangHoaThongSoService.delete(id) ? ResponseEntity.ok("Success") : ResponseEntity.ok("Failed");
    }
}
