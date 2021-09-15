package com.mpec.hanghoathongso.service;

import com.mpec.hanghoathongso.entities.ThuongHieu;
import com.mpec.hanghoathongso.entities.entitiesDTO.ThuongHieuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ThuongHieuService {

    Page<ThuongHieu> findAll(Pageable pageable);

    Optional<ThuongHieu> findById(Integer id);

    Page<ThuongHieu> search(String text, Pageable pageable);

    Optional<ThuongHieu> save(ThuongHieuDTO thuongHieu);

    Optional<ThuongHieu> update(ThuongHieuDTO thuongHieu);

    Boolean delete(Integer id);
}
