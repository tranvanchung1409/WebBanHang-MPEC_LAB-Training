package com.mpec.hanghoathongso.service;

import com.mpec.hanghoathongso.entities.NhomHang;
import com.mpec.hanghoathongso.entities.entitiesDTO.NhomHangDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NhomHangService {

    Page<NhomHang> findAll(Pageable pageable);

    Optional<NhomHang> findById(Integer id);

    Page<NhomHang> search(String text, Pageable pageable);

    Optional<NhomHang> save(NhomHangDTO nhomHang);

    Optional<NhomHang> update(NhomHangDTO nhomHang);

    Boolean delete(Integer id);
}
