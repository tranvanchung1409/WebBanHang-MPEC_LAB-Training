package com.mpec.hanghoathongso.repository;

import com.mpec.hanghoathongso.entities.ThongSoKiThuat;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ThongSoKiThuatRepo extends JpaRepository<ThongSoKiThuat, Integer> {

    @Query(value = "from ThongSoKiThuat tskt where tskt.xoa = false")
    Page<ThongSoKiThuat> findAll(Pageable pageable);

    @Query(value = "from ThongSoKiThuat tskt where tskt.id = ?1 and tskt.xoa = false")
    Optional<ThongSoKiThuat> findById(Integer id);

    @Query(value = "from ThongSoKiThuat tskt where " +
            "(?1 is null or tskt.tenThongSo like concat('%' , ?1 , '%'))" +
            "and tskt.xoa = false")
    Page<ThongSoKiThuat> search(String text, Pageable pageable);

    @Query(value = "from ThongSoKiThuat tskt where " +
            "(?1 is null or tskt.nhomThongSo.id =?1 and tskt.nhomThongSo.xoa = false)" +
            "and tskt.xoa = false")
    Page<ThongSoKiThuat> findByNhomThongSoId(Integer nhomThongSoId, Pageable pageable);

//    @Query(value = "from ThongSoKiThuat tskt where " +
//            "(?1 is null or tskt.nhomThongSo.tenNhomThongSo like concat('%' , ?1 , '%') and tskt.nhomThongSo.xoa = false)" +
//            "and tskt.xoa = false")
//    Page<ThongSoKiThuat> findByNhomThongSo(String tenNhomThongSo, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update ThongSoKiThuat tskt set tskt.xoa = true where tskt.id = ?1")
    Integer delete(Integer id);
}
