package com.mpec.hanghoathongso.serviceImpl;

import com.mpec.hanghoathongso.entities.NhomHang;
import com.mpec.hanghoathongso.entities.NhomThongSo;
import com.mpec.hanghoathongso.entities.ThongSoKiThuat;
import com.mpec.hanghoathongso.entities.entitiesDTO.ThongSoKiThuatDTO;
import com.mpec.hanghoathongso.repository.NhomHangRepo;
import com.mpec.hanghoathongso.repository.NhomThongSoRepo;
import com.mpec.hanghoathongso.repository.ThongSoKiThuatRepo;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.ThongSoKiThuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThongSoKiThuatServiceImpl implements ThongSoKiThuatService {

    @Autowired
    ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Autowired
    NhomHangRepo nhomHangRepo;

    @Autowired
    NhomThongSoRepo nhomThongSoRepo;

    @Override
    public Page<ThongSoKiThuat> findAll(Pageable pageable) {
        try {
            return thongSoKiThuatRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<ThongSoKiThuat> findById(Integer id) {
        try {
            return thongSoKiThuatRepo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<ThongSoKiThuat> search(String text, Pageable pageable) {
        try {
            return thongSoKiThuatRepo.search(text, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Page<ThongSoKiThuat> findByNhomThongSoId(Integer nhomThongSoId, Pageable pageable) {
        try {
            return thongSoKiThuatRepo.findByNhomThongSoId(nhomThongSoId, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public MyResponse save(ThongSoKiThuatDTO thongSoKiThuat) {
        MyResponse response = new MyResponse();

        Optional<NhomHang> nhomHang = nhomHangRepo.findById(thongSoKiThuat.getNhomHangId());
        if (!nhomHang.isPresent()) {
            response.setResult(-1, "Nhóm hàng không tồn tại");
            return response;
        }
        Optional<NhomThongSo> nhomThongSo = nhomThongSoRepo.findById(thongSoKiThuat.getNhomThongSoId());
        if (!nhomThongSo.isPresent()) {
            response.setResult(-1, "Nhóm thông số không tồn tại");
            return response;
        } else {
            ThongSoKiThuat thongSoKiThuat1 = new ThongSoKiThuat();
            thongSoKiThuat1.setTenThongSo(thongSoKiThuat.getTenThongSo());
            thongSoKiThuat1.setNhomHang(nhomHang.get());
            thongSoKiThuat1.setXoa(false);
            thongSoKiThuat1.setNhomThongSo(nhomThongSo.get());
            ThongSoKiThuat thongSoKiThuat2 = thongSoKiThuatRepo.save(thongSoKiThuat1);
            if (thongSoKiThuat2 == null) {
                response.setResult(-1, "Lưu thông số kĩ thuật không thành công");
                return response;
            } else {
                response.success();
            }
        }
        return response;
    }

    @Override
    public MyResponse update(ThongSoKiThuatDTO thongSoKiThuat) {
        MyResponse response = new MyResponse();

        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(thongSoKiThuat.getId());
        if (thongSoKiThuatOptional.isPresent()) {
            ThongSoKiThuat thongSoKiThuat1 = thongSoKiThuatOptional.get();
            if (thongSoKiThuat.getTenThongSo() != null) {
                thongSoKiThuat1.setTenThongSo(thongSoKiThuat.getTenThongSo());
            }
            if (thongSoKiThuat.getNhomHangId() != null) {
                Optional<NhomHang> nhomHangOptional = nhomHangRepo.findById(thongSoKiThuat.getNhomHangId());
                if (nhomHangOptional.isPresent()) {
                    thongSoKiThuat1.setNhomHang(nhomHangOptional.get());
                } else {
                    response.setResult(-1, "Nhóm hàng không tồn tại");
                    return response;
                }
            }
            if (thongSoKiThuat.getNhomThongSoId() != null) {
                Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoRepo.findById(thongSoKiThuat.getNhomThongSoId());
                if (nhomThongSoOptional.isPresent()) {
                    thongSoKiThuat1.setNhomThongSo(nhomThongSoOptional.get());
                } else {
                    response.setResult(-1, "Nhóm thông số không tồn tại");
                    return response;
                }
            }
            Optional<ThongSoKiThuat> thongSoKiThuat2 = Optional.ofNullable(thongSoKiThuatRepo.save(thongSoKiThuat1));
            if (thongSoKiThuat2.isPresent()) {
                response.success();
            }
        } else {
            response.setResult(-1, "Update không thành công");
            return response;
        }
        return response;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            return thongSoKiThuatRepo.delete(id) >= 0;
        } catch (Exception e) {
            return false;
        }
    }
}
