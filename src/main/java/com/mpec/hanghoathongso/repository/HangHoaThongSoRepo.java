package com.mpec.hanghoathongso.repository;

import com.mpec.hanghoathongso.entities.HangHoaThongSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface HangHoaThongSoRepo extends JpaRepository<HangHoaThongSo, Integer> {

    @Query(value = "from HangHoaThongSo hhts where hhts.xoa = false")
    Page<HangHoaThongSo> findAll(Pageable pageable);

    @Query(value = "from HangHoaThongSo hhts where hhts.id = ?1 and hhts.xoa = false")
    Optional<HangHoaThongSo> findById(Integer id);

    @Query(value = "from HangHoaThongSo hhts where " +
            "(?1 is null or hhts.hangHoa.id = ?1 and hhts.hangHoa.xoa = false)" +
            "and hhts.xoa = false")
    Page<HangHoaThongSo> findByHangHoaId(Integer hangHoaId, Pageable pageable);

    @Query(value = "from HangHoaThongSo hhts where " +
            "(?1 is null or hhts.thongSoKiThuat.id = ?1 and hhts.thongSoKiThuat.xoa = false)" +
            "and hhts.xoa = false")
    Page<HangHoaThongSo> findByThongSoKiThuatId(Integer thongSoKiThuatId, Pageable pageable);

    @Query(value = "from HangHoaThongSo hhts where " +
            "(?1 is null or hhts.thongSoChiTiet.id = ?1 and hhts.thongSoChiTiet.xoa = false)" +
            "and hhts.xoa = false")
    Page<HangHoaThongSo> findByThongSoChiTietId(Integer thongSoChiTietId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update HangHoaThongSo hhts set hhts.xoa = true where hhts.id = ?1")
    Integer delete(Integer id);
}
