package com.mpec.hanghoathongso.service;

import com.mpec.hanghoathongso.entities.HangHoaThongSo;
import com.mpec.hanghoathongso.entities.entitiesDTO.HangHoaDTO;
import com.mpec.hanghoathongso.entities.entitiesDTO.HangHoaThongSoDTO;
import com.mpec.hanghoathongso.response.MyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface HangHoaThongSoService {

    Page<HangHoaThongSo> findAll(Pageable pageable);

    Optional<HangHoaThongSo> findById(Integer id);

    Page<HangHoaThongSo> findByHangHoaId(Integer hangHoaId, Pageable pageable);

    Page<HangHoaThongSo> findByThongSoKiThuatId(Integer thongSoKiThuatId, Pageable pageable);

    Page<HangHoaThongSo> findByThongSoChiTietId(Integer thongSoChiTietId, Pageable pageable);

    MyResponse save(HangHoaThongSoDTO hangHoaThongSo);

    MyResponse update(HangHoaThongSoDTO hangHoaThongSo);

    Boolean delete(Integer id);
}
