package com.mpec.hanghoathongso.controller;

import com.mpec.hanghoathongso.entities.ThongSoKiThuat;
import com.mpec.hanghoathongso.entities.entitiesDTO.ThongSoKiThuatDTO;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.ThongSoKiThuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/thong-so-ki-thuat")
public class ThongSoKiThuatController {

    @Autowired
    ThongSoKiThuatService thongSoKiThuatService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThongSoKiThuat> thongSoKiThuatPage = thongSoKiThuatService.findAll(pageable);
        return ResponseEntity.ok(thongSoKiThuatPage);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id", required = false) Integer id) {
        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatService.findById(id);
        if (thongSoKiThuatOptional.isPresent()) {
            return ResponseEntity.ok(thongSoKiThuatOptional.get());
        } else {
            return ResponseEntity.ok("Thông số kĩ thuật không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text", defaultValue = "", required = false) String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThongSoKiThuat> thongSoKiThuatPage = thongSoKiThuatService.search(text, pageable);
        return ResponseEntity.ok(thongSoKiThuatPage);
    }

    @GetMapping("/find-by-nhom-thong-so")
    public ResponseEntity<?> findByNhomThongSoId(@RequestParam(name = "nhom-thong-so-id", defaultValue = "", required = false) Integer nhomThongSoId,
                                                 @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                                 @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThongSoKiThuat> thongSoKiThuatPage = thongSoKiThuatService.findByNhomThongSoId(nhomThongSoId, pageable);
        return ResponseEntity.ok(thongSoKiThuatPage);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ThongSoKiThuatDTO thongSoKiThuat) {
        MyResponse response = thongSoKiThuatService.save(thongSoKiThuat);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ThongSoKiThuatDTO thongSoKiThuat) {
        MyResponse response = thongSoKiThuatService.update(thongSoKiThuat);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/xoa")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        return thongSoKiThuatService.delete(id) ? ResponseEntity.ok("Success") : ResponseEntity.ok("Failed");
    }
}
