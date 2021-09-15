package com.mpec.hanghoathongso.service;

import com.mpec.hanghoathongso.entities.NhomThongSo;
import com.mpec.hanghoathongso.entities.entitiesDTO.NhomThongSoDTO;
import com.mpec.hanghoathongso.response.MyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NhomThongSoService {

    Page<NhomThongSo> findAll(Pageable pageable);

    Optional<NhomThongSo> findById(Integer id);

    Page<NhomThongSo> search(String text, Pageable pageable);

    Page<NhomThongSo> findByHangHoaId(Integer hangHoaId, Pageable pageable);

    MyResponse save(NhomThongSoDTO nhomThongSo);

    Optional<NhomThongSo> update(NhomThongSoDTO nhomThongSo);

    Boolean delete(Integer id);
}
