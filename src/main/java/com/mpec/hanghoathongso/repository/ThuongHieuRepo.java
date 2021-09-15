package com.mpec.hanghoathongso.repository;

import com.mpec.hanghoathongso.entities.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ThuongHieuRepo extends JpaRepository<ThuongHieu, Integer> {
    @Query(value = "from ThuongHieu th where th.xoa = false")
    Page<ThuongHieu> findAll(Pageable pageable);

    @Query(value = "from ThuongHieu th where th.id = ?1 and th.xoa = false")
    Optional<ThuongHieu> findById(Integer id);

    @Query(value = "from ThuongHieu th where " +
            "(?1 is null or th.tenThuongHieu like concat('%',?1, '%'))" +
            "and th.xoa = false")
    Page<ThuongHieu> search(String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update ThuongHieu th set th.xoa = true where th.id = ?1")
    Integer delete(Integer id);
}
