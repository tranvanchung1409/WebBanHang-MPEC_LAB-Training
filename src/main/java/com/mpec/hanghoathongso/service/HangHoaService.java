package com.mpec.hanghoathongso.service;

import com.mpec.hanghoathongso.entities.HangHoa;
import com.mpec.hanghoathongso.entities.entitiesDTO.HangHoaDTO;
import com.mpec.hanghoathongso.response.MyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface HangHoaService {

    Page<HangHoa> findAll(Pageable pageable);

    Optional<HangHoa> findById(Integer id);

//    Optional<HangHoa> save

    Page<HangHoa> search(String text, Pageable pageable);

//    Page<HangHoa> findByNhomHang(String nhomHang, String thuongHieu, Pageable pageable);
//
//    Page<HangHoa> findByThuongHieu(String thuongHieu, Pageable pageable);

    List<HangHoa> findByNhomHangId(Integer nhomHangId);

    List<HangHoa> findByThuongHieuId(Integer thuongHieuId);

    MyResponse save(HangHoaDTO hangHoa);

//    Optional<HangHoa> update(HangHoaDTO hangHoa);

    MyResponse update(HangHoaDTO hangHoa);

    Boolean delete(Integer id);
}
