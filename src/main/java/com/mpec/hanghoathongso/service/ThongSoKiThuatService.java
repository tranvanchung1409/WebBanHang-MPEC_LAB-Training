package com.mpec.hanghoathongso.service;

import com.mpec.hanghoathongso.entities.ThongSoKiThuat;
import com.mpec.hanghoathongso.entities.entitiesDTO.ThongSoKiThuatDTO;
import com.mpec.hanghoathongso.response.MyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ThongSoKiThuatService {

    Page<ThongSoKiThuat> findAll(Pageable pageable);

    Optional<ThongSoKiThuat> findById(Integer id);

    Page<ThongSoKiThuat> search(String text, Pageable pageable);

    Page<ThongSoKiThuat> findByNhomThongSoId(Integer nhomThongSoId, Pageable pageable);

    MyResponse save(ThongSoKiThuatDTO thongSoKiThuat);

    //    Optional<ThongSoKiThuat> update(ThongSoKiThuatDTO thongSoKiThuat);

    MyResponse update(ThongSoKiThuatDTO thongSoKiThuat);

    Boolean delete(Integer id);
}
