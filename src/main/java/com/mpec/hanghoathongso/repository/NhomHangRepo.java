package com.mpec.hanghoathongso.repository;

import com.mpec.hanghoathongso.entities.NhomHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface NhomHangRepo extends JpaRepository<NhomHang, Integer> {

    @Query(value = "from NhomHang nh where nh.xoa = false")
    Page<NhomHang> findAll(Pageable pageable);

    @Query(value = "from NhomHang nh where nh.id = ?1 and nh.xoa = false")
    Optional<NhomHang> findById(Integer id);

    @Query(value = "from NhomHang nh where " +
            "(?1 is null or nh.tenNhomHang like concat('%',?1, '%'))" +
            "and nh.xoa = false")
    Page<NhomHang> search(String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update NhomHang nh set nh.xoa = true where nh.id = ?1")
    Integer delete(Integer id);
}
