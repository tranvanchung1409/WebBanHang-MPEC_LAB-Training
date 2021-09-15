package com.mpec.hanghoathongso.serviceImpl;

import com.mpec.hanghoathongso.entities.HangHoa;
import com.mpec.hanghoathongso.entities.NhomThongSo;
import com.mpec.hanghoathongso.entities.entitiesDTO.NhomThongSoDTO;
import com.mpec.hanghoathongso.repository.HangHoaRepo;
import com.mpec.hanghoathongso.repository.NhomThongSoRepo;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.NhomThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NhomThongSoServiceImpl implements NhomThongSoService {

    @Autowired
    NhomThongSoRepo nhomThongSoRepo;

    @Autowired
    HangHoaRepo hangHoaRepo;

    @Override
    public Page<NhomThongSo> findAll(Pageable pageable) {
        try {
            return nhomThongSoRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomThongSo> findById(Integer id) {
        try {
            return nhomThongSoRepo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<NhomThongSo> search(String text, Pageable pageable) {
        try {
            return nhomThongSoRepo.search(text, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Page<NhomThongSo> findByHangHoaId(Integer hangHoaId, Pageable pageable) {
        try {
                return nhomThongSoRepo.findByHangHoaId(hangHoaId, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public MyResponse save(NhomThongSoDTO nhomThongSo) {
        MyResponse response = new MyResponse();
        try {
            Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(nhomThongSo.getHangHoaId());
            if (!hangHoaOptional.isPresent()) {
                response.setResult(-1, "Hàng hóa không tồn tại");
                return response;
            }
            NhomThongSo nhomThongSo1 = new NhomThongSo();
            nhomThongSo1.setId(nhomThongSo1.getId());
            nhomThongSo1.setTenNhomThongSo(nhomThongSo.getTenNhomThongSo());
            nhomThongSo1.setHangHoa(hangHoaOptional.get());
            nhomThongSo1.setMaNhomThongSo(nhomThongSo.getMaNhomThongSo());
            nhomThongSo1.setXoa(false);
            NhomThongSo nhomThongSo2 = nhomThongSoRepo.save(nhomThongSo1);
            if (nhomThongSo2 == null) {
                response.setResult(-1, "Lưu nhóm thông số không thành công");
                return response;
            } else {
                response.success();
            }
        } catch (Exception e) {
            response.setResult(-1, "Lưu nhóm thông số không thành công");
            return response;
        }
        return response;
    }

    @Override
    public Optional<NhomThongSo> update(NhomThongSoDTO nhomThongSo) {
        try {
            Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoRepo.findById(nhomThongSo.getId());
            if (nhomThongSoOptional.isPresent()) {
                NhomThongSo nhomThongSo1 = nhomThongSoOptional.get();
                if (nhomThongSo.getTenNhomThongSo() != null) {
                    nhomThongSo1.setTenNhomThongSo(nhomThongSo.getTenNhomThongSo());
                }
                if (nhomThongSo.getMaNhomThongSo() != null) {
                    nhomThongSo1.setMaNhomThongSo(nhomThongSo.getMaNhomThongSo());
                }
                if (nhomThongSo.getHangHoaId() != null) {
                    Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(nhomThongSo.getHangHoaId());
                    nhomThongSo1.setHangHoa(hangHoaOptional.get());
                }
                return Optional.ofNullable(nhomThongSoRepo.save(nhomThongSo1));
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
            return nhomThongSoRepo.delete(id) >= 0;
        } catch (Exception e) {
            return false;
        }
    }
}
