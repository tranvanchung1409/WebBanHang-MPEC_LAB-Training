package com.mpec.hanghoathongso.repository;

import com.mpec.hanghoathongso.entities.HangHoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface HangHoaRepo extends JpaRepository<HangHoa, Integer> {

    @Query(value = "from HangHoa hh where hh.xoa = false")
    Page<HangHoa> findAll(Pageable pageable);

    @Query(value = "from HangHoa hh where hh.id = ?1 and hh.xoa = false")
    Optional<HangHoa> findById(Integer id);

    @Query(value = "from HangHoa hh where " +
            "(?1 is null or hh.tenHangHoa like concat('%',?1, '%'))" +
            "and hh.xoa = false")
    Page<HangHoa> search(String text, Pageable pageable);

//    @Query(value = "from HangHoa hh where " +
//            "(?1 is null or hh.nhomHang.tenNhomHang like concat('%',?1, '%') and hh.nhomHang.xoa = false )" +
//            "and hh.xoa = false")
//    Page<HangHoa> findByTenNhomHang(String nhomHang, String thuongHieu, Pageable pageable);

    @Query(value = "from HangHoa hh where (hh.nhomHang.id = ?1 and hh.nhomHang.xoa = false)" +
            "and hh.xoa = false")
    List<HangHoa> findByNhomHangId(Integer nhomHangId);

//    @Query(value = "from HangHoa hh where " +
//            "(?1 is null or hh.thuongHieu.tenThuongHieu like concat('%',?1, '%') and hh.thuongHieu.xoa = false)" +
//            "and hh.xoa = false")
//    Page<HangHoa> findByTenThuongHieu(String tenThuongHieu, Pageable pageable);

    @Query(value = "from HangHoa hh where (hh.thuongHieu.id = ?1 and hh.thuongHieu.xoa = false)" +
            "and hh.xoa = false")
    List<HangHoa> findByThuongHieuId(Integer thuongHieuId);

    @Modifying
    @Transactional
    @Query("update HangHoa nh set nh.xoa = true where nh.id = ?1")
    Integer delete(Integer id);
}
