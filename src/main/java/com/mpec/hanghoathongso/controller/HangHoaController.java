package com.mpec.hanghoathongso.controller;

import com.mpec.hanghoathongso.entities.HangHoa;
import com.mpec.hanghoathongso.entities.entitiesDTO.HangHoaDTO;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.HangHoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/hang-hoa")
public class HangHoaController {

    @Autowired
    HangHoaService hangHoaService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HangHoa> hangHoaPage = hangHoaService.findAll(pageable);
        return ResponseEntity.ok(hangHoaPage);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id", required = false) Integer id) {
        Optional<HangHoa> hangHoaOptional = hangHoaService.findById(id);
        if (hangHoaOptional.isPresent()) {
            return ResponseEntity.ok(hangHoaOptional.get());
        } else {
            return ResponseEntity.ok("Hàng hóa không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text", defaultValue = "", required = false) String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HangHoa> hangHoaPage = hangHoaService.search(text, pageable);
        return ResponseEntity.ok(hangHoaPage);
    }

    @GetMapping("/find-by-nhom-hang")
    public ResponseEntity<?> findByThongSoKiThuatId(@RequestParam(name = "nhom-hang-id", defaultValue = "", required = false) Integer nhonHangId,
                                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
        List<HangHoa> hangHoas = hangHoaService.findByNhomHangId(nhonHangId);
        return ResponseEntity.ok(hangHoas);
    }

    @GetMapping("/find-by-thuong-hieu")
    public ResponseEntity<?> findByThuongHieuId(@RequestParam(name = "thuong-hieu-id", defaultValue = "", required = false) Integer thuongHieuId,
                                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
        List<HangHoa> hangHoas = hangHoaService.findByThuongHieuId(thuongHieuId);
        return ResponseEntity.ok(hangHoas);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HangHoaDTO hangHoa) {
        MyResponse response = hangHoaService.save(hangHoa);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody HangHoaDTO hangHoa) {
        MyResponse response = hangHoaService.update(hangHoa);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/xoa")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        return hangHoaService.delete(id) ? ResponseEntity.ok("Success") : ResponseEntity.ok("Failed");
    }


}
