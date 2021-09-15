package com.mpec.hanghoathongso.serviceImpl;

import com.mpec.hanghoathongso.entities.HangHoa;
import com.mpec.hanghoathongso.entities.ThuongHieu;
import com.mpec.hanghoathongso.entities.entitiesDTO.ThuongHieuDTO;
import com.mpec.hanghoathongso.repository.HangHoaRepo;
import com.mpec.hanghoathongso.repository.ThuongHieuRepo;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    ThuongHieuRepo thuongHieuRepo;

    @Autowired
    HangHoaRepo hangHoaRepo;

    @Override
    public Page<ThuongHieu> findAll(Pageable pageable) {
        try {
            return thuongHieuRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<ThuongHieu> findById(Integer id) {
        try {
            Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepo.findById(id);
            if (!thuongHieuOptional.isPresent()) {
                return Optional.empty();
            }
            List<HangHoa> hangHoas = hangHoaRepo.findByThuongHieuId(id);
            thuongHieuOptional.get().setHangHoas(hangHoas);
            return thuongHieuOptional;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<ThuongHieu> search(String text, Pageable pageable) {
        try {
            return thuongHieuRepo.search(text, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<ThuongHieu> save(ThuongHieuDTO thuongHieu) {
        try {
            ThuongHieu thuongHieu1 = new ThuongHieu();
            thuongHieu1.setId(thuongHieu.getId());
            thuongHieu1.setTenThuongHieu(thuongHieu.getTenThuongHieu());
            thuongHieu1.setXoa(false);
            return Optional.ofNullable(thuongHieuRepo.save(thuongHieu1));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ThuongHieu> update(ThuongHieuDTO thuongHieu) {
        try {
            Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepo.findById(thuongHieu.getId());
            if (thuongHieuOptional.isPresent()) {
                ThuongHieu thuongHieu1 = thuongHieuOptional.get();
                if (thuongHieu.getTenThuongHieu() != null) {
                    thuongHieu1.setTenThuongHieu(thuongHieu.getTenThuongHieu());
                }
                return Optional.ofNullable(thuongHieuRepo.save(thuongHieu1));
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
            return thuongHieuRepo.delete(id) >= 0;
        } catch (Exception e) {
            return false;
        }
    }
}
