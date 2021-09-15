package com.mpec.hanghoathongso.serviceImpl;

import com.mpec.hanghoathongso.entities.ThongSoChiTiet;
import com.mpec.hanghoathongso.entities.ThongSoKiThuat;
import com.mpec.hanghoathongso.entities.entitiesDTO.ThongSoChiTietDTO;
import com.mpec.hanghoathongso.repository.ThongSoChiTietRepo;
import com.mpec.hanghoathongso.repository.ThongSoKiThuatRepo;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.ThongSoChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThongSoChiTietServiceImpl implements ThongSoChiTietService {

    @Autowired
    ThongSoChiTietRepo thongSoChiTietRepo;

    @Autowired
    ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Override
    public Page<ThongSoChiTiet> findAll(Pageable pageable) {
        try {
            return thongSoChiTietRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<ThongSoChiTiet> findById(Integer id) {
        try {
            return thongSoChiTietRepo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<ThongSoChiTiet> search(String text, Pageable pageable) {
        try {
            return thongSoChiTietRepo.search(text, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Page<ThongSoChiTiet> findByThongSoKiThuatId(Integer thongSoKiThuatId, Pageable pageable) {
        try {
            return thongSoChiTietRepo.findByThongSoKiThuatId(thongSoKiThuatId, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public MyResponse save(ThongSoChiTietDTO thongSoChiTiet) {
        MyResponse response = new MyResponse();

        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(thongSoChiTiet.getThongSoKiThuatId());
        if (!thongSoKiThuatOptional.isPresent()) {
            response.setResult(-1, "Thông số kĩ thuật không tồn tại");
            return response;
        } else {
            ThongSoChiTiet thongSoChiTiet1 = new ThongSoChiTiet();
            thongSoChiTiet1.setId(thongSoChiTiet.getId());
            thongSoChiTiet1.setTen(thongSoChiTiet.getTen());
            thongSoChiTiet1.setGiaTri(thongSoChiTiet.getGiaTri());
            thongSoChiTiet1.setThongSoKiThuat(thongSoKiThuatOptional.get());
            thongSoChiTiet1.setXoa(false);
            ThongSoChiTiet thongSoChiTiet2 = thongSoChiTietRepo.save(thongSoChiTiet1);
            if (thongSoChiTiet2 == null) {
                response.setResult(-1, "Lưu thông số chi tiết không thành công");
                return response;
            } else {
                response.success();
            }
        }
        return response;
    }

    @Override
    public MyResponse update(ThongSoChiTietDTO thongSoChiTiet) {
        MyResponse response = new MyResponse();

        Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietRepo.findById(thongSoChiTiet.getId());
        if (thongSoChiTietOptional.isPresent()) {
            ThongSoChiTiet thongSoChiTiet1 = thongSoChiTietOptional.get();
            if (thongSoChiTiet1.getTen() != null) {
                thongSoChiTiet1.setTen(thongSoChiTiet.getTen());
            }
            if (thongSoChiTiet1.getGiaTri() != null) {
                thongSoChiTiet1.setGiaTri(thongSoChiTiet.getGiaTri());
            }
            if (thongSoChiTiet1.getThongSoKiThuat() != null) {
                Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(thongSoChiTiet.getThongSoKiThuatId());
                if (thongSoKiThuatOptional.isPresent()) {
                    thongSoChiTiet1.setThongSoKiThuat(thongSoKiThuatOptional.get());
                } else {
                    response.setResult(-1, "Thông số kĩ thuật không tồn tại");
                }
            }
            Optional<ThongSoChiTiet> thongSoChiTiet2 = Optional.ofNullable(thongSoChiTietRepo.save(thongSoChiTiet1));
            if (thongSoChiTiet2.isPresent()) {
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
