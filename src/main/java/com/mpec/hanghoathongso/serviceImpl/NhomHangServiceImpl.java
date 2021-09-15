package com.mpec.hanghoathongso.serviceImpl;

import com.mpec.hanghoathongso.entities.HangHoa;
import com.mpec.hanghoathongso.entities.NhomHang;
import com.mpec.hanghoathongso.entities.entitiesDTO.NhomHangDTO;
import com.mpec.hanghoathongso.repository.HangHoaRepo;
import com.mpec.hanghoathongso.repository.NhomHangRepo;
import com.mpec.hanghoathongso.service.NhomHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhomHangServiceImpl implements NhomHangService {

    @Autowired
    NhomHangRepo nhomHangRepo;

    @Autowired
    HangHoaRepo hangHoaRepo;

    @Override
    public Page<NhomHang> findAll(Pageable pageable) {
        try {
            return nhomHangRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomHang> findById(Integer id) {
        try {
            Optional<NhomHang> nhomHangOptional = nhomHangRepo.findById(id);
            if (!nhomHangOptional.isPresent()) {
                return Optional.empty();
            }
            List<HangHoa> hangHoas = hangHoaRepo.findByNhomHangId(id);
            nhomHangOptional.get().setHangHoas(hangHoas);
            return nhomHangOptional;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<NhomHang> search(String text, Pageable pageable) {
        try {
            return nhomHangRepo.search(text, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomHang> save(NhomHangDTO nhomHang) {
        try {
            NhomHang nhomHang1 = new NhomHang();
            nhomHang1.setId(nhomHang.getId());
            nhomHang1.setTenNhomHang(nhomHang.getTenNhomHang());
            nhomHang1.setMaNhomHang(nhomHang1.getMaNhomHang());
            nhomHang1.setXoa(false);
            return Optional.ofNullable(nhomHangRepo.save(nhomHang1));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<NhomHang> update(NhomHangDTO nhomHang) {
        try {
            Optional<NhomHang> nhomHangOptional = nhomHangRepo.findById(nhomHang.getId());
            if (nhomHangOptional.isPresent()) {
                NhomHang nhomHang1 = nhomHangOptional.get();
                if (nhomHang.getTenNhomHang() != null) {
                    nhomHang1.setTenNhomHang(nhomHang.getTenNhomHang());
                }
                if (nhomHang.getMaNhomHang() != null) {
                    nhomHang1.setMaNhomHang(nhomHang.getMaNhomHang());
                }
                return Optional.ofNullable(nhomHangRepo.save(nhomHang1));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            return nhomHangRepo.delete(id) >= 0;
        } catch (Exception e) {
            return false;
        }
    }
}
