package com.mpec.hanghoathongso.service;

import com.mpec.hanghoathongso.entities.ThongSoChiTiet;
import com.mpec.hanghoathongso.entities.entitiesDTO.ThongSoChiTietDTO;
import com.mpec.hanghoathongso.response.MyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ThongSoChiTietService {

    Page<ThongSoChiTiet> findAll(Pageable pageable);

    Optional<ThongSoChiTiet> findById(Integer id);

    Page<ThongSoChiTiet> search(String text, Pageable pageable);

    Page<ThongSoChiTiet> findByThongSoKiThuatId(Integer thongSoKiThuatId, Pageable pageable);

    MyResponse save(ThongSoChiTietDTO thongSoChiTiet);

//    Optional<ThongSoChiTiet> update(ThongSoChiTietDTO thongSoChiTiet);

    MyResponse update(ThongSoChiTietDTO thongSoChiTiet);

    Boolean delete(Integer id);
}
