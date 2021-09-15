package com.mpec.hanghoathongso.controller;

import com.mpec.hanghoathongso.entities.ThongSoChiTiet;
import com.mpec.hanghoathongso.entities.entitiesDTO.ThongSoChiTietDTO;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.ThongSoChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/thong-so-chi-tiet")
public class ThongSoChiTietController {

    @Autowired
    ThongSoChiTietService thongSoChiTietService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThongSoChiTiet> thongSoChiTietPage = thongSoChiTietService.findAll(pageable);
        return ResponseEntity.ok(thongSoChiTietPage);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id", required = false) Integer id) {
        Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietService.findById(id);
        if (thongSoChiTietOptional.isPresent()) {
            return ResponseEntity.ok(thongSoChiTietOptional.get());
        } else {
            return ResponseEntity.ok("Thông số chi tiết không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text", defaultValue = "", required = false) String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThongSoChiTiet> thongSoChiTietPage = thongSoChiTietService.search(text, pageable);
        return ResponseEntity.ok(thongSoChiTietPage);
    }

    @GetMapping("/find-by-thong-so-ki-thuat")
    public ResponseEntity<?> findByThongSoKiThuatId(@RequestParam(name = "thong-so-ki-thuat-id", defaultValue = "", required = false) Integer thongSoKiThuatId,
                                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThongSoChiTiet> thongSoChiTietPage = thongSoChiTietService.findByThongSoKiThuatId(thongSoKiThuatId, pageable);
        return ResponseEntity.ok(thongSoChiTietPage);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ThongSoChiTietDTO thongSoChiTiet) {
        MyResponse response = thongSoChiTietService.save(thongSoChiTiet);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ThongSoChiTietDTO thongSoChiTiet) {
        MyResponse response = thongSoChiTietService.update(thongSoChiTiet);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/xoa")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        return thongSoChiTietService.delete(id) ? ResponseEntity.ok("Success") : ResponseEntity.ok("Failed");
    }
}
