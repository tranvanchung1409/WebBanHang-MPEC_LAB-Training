package com.mpec.hanghoathongso.serviceImpl;

import com.mpec.hanghoathongso.entities.HangHoa;
import com.mpec.hanghoathongso.entities.HangHoaThongSo;
import com.mpec.hanghoathongso.entities.ThongSoChiTiet;
import com.mpec.hanghoathongso.entities.ThongSoKiThuat;
import com.mpec.hanghoathongso.entities.entitiesDTO.HangHoaThongSoDTO;
import com.mpec.hanghoathongso.repository.HangHoaRepo;
import com.mpec.hanghoathongso.repository.HangHoaThongSoRepo;
import com.mpec.hanghoathongso.repository.ThongSoChiTietRepo;
import com.mpec.hanghoathongso.repository.ThongSoKiThuatRepo;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.HangHoaThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HangHoaThongSoServiceImpl implements HangHoaThongSoService {

    @Autowired
    HangHoaThongSoRepo hangHoaThongSoRepo;

    @Autowired
    HangHoaRepo hangHoaRepo;

    @Autowired
    ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Autowired
    ThongSoChiTietRepo thongSoChiTietRepo;

    @Override
    public Page<HangHoaThongSo> findAll(Pageable pageable) {
        try {
            return hangHoaThongSoRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoaThongSo> findById(Integer id) {
        try {
            return hangHoaThongSoRepo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<HangHoaThongSo> findByHangHoaId(Integer hangHoaId, Pageable pageable) {
        try {
            return hangHoaThongSoRepo.findByHangHoaId(hangHoaId, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Page<HangHoaThongSo> findByThongSoKiThuatId(Integer thongSoKiThuatId, Pageable pageable) {
        try {
            return hangHoaThongSoRepo.findByThongSoKiThuatId(thongSoKiThuatId, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Page<HangHoaThongSo> findByThongSoChiTietId(Integer thongSoChiTietId, Pageable pageable) {
        try {
            return hangHoaThongSoRepo.findByThongSoChiTietId(thongSoChiTietId, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public MyResponse save(HangHoaThongSoDTO hangHoaThongSo) {
        MyResponse response = new MyResponse();

        Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(hangHoaThongSo.getHangHoaId());
        if (hangHoaOptional.isEmpty()) {
            response.setResult(-1, "Hàng hóa không tồn tại");
            return response;
        }
        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(hangHoaThongSo.getThongSoKiThuatid());
        if (thongSoKiThuatOptional.isEmpty()) {
            response.setResult(-1, "Thông số kĩ thuật không tồn tại");
            return response;
        } else {
            HangHoaThongSo hangHoaThongSo1 = new HangHoaThongSo();
            Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietRepo.findById(hangHoaThongSo.getThongSoChiTietId());
            hangHoaThongSo1.setHangHoa(hangHoaOptional.get());
            hangHoaThongSo1.setThongSoKiThuat(thongSoKiThuatOptional.get());
            hangHoaThongSo1.setThongSoChiTiet(thongSoChiTietOptional.get());
            hangHoaThongSo1.setGiaTri(hangHoaThongSo.getGiaTri());
            hangHoaThongSo1.setXoa(false);

            HangHoaThongSo hangHoaThongSo2 = hangHoaThongSoRepo.save(hangHoaThongSo1);
            if (hangHoaThongSo2 == null) {
                response.setResult(-1, "Lưu thông số hàng hóa không thành công");
                return response;
            }
            response.success();
        }
        return response;
    }

    @Override
    public MyResponse update(HangHoaThongSoDTO hangHoaThongSo) {
        MyResponse response = new MyResponse();

        Optional<HangHoaThongSo> hangHoaThongSoOptional = hangHoaThongSoRepo.findById(hangHoaThongSo.getId());
        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(hangHoaThongSo.getThongSoKiThuatid());
        Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietRepo.findById(hangHoaThongSo.getThongSoChiTietId());

        if (hangHoaThongSoOptional.isPresent()) {
            HangHoaThongSo hangHoaThongSo1 = hangHoaThongSoOptional.get();
            if (hangHoaThongSo.getHangHoaId() != null) {
                Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(hangHoaThongSo.getHangHoaId());
                if (hangHoaOptional.isPresent()) {
                    hangHoaThongSo1.setHangHoa(hangHoaOptional.get());
                } else {
                    response.setResult(-1, "Hàng hóa không tồn tại");
                    return response;
                }
            }
            if (hangHoaThongSo.getThongSoKiThuatid() != null) {
                if (thongSoKiThuatOptional.isPresent()) {
                    hangHoaThongSo1.setThongSoKiThuat(thongSoKiThuatOptional.get());
                } else {
                    response.setResult(-1, "Thông số kĩ thuật không tồn tại");
                    return response;
                }
            }
            if (hangHoaThongSo.getThongSoChiTietId() != null) {
                if (thongSoChiTietOptional.isPresent()) {
                    if (thongSoChiTietOptional.get().getThongSoKiThuat() != thongSoKiThuatOptional.get()) {
                        response.setResult(-1, "Thông số chi tiết không thuộc thông số kĩ thuật đang chọn");
                        return response;
                    }
                }
                hangHoaThongSo1.setThongSoChiTiet(thongSoChiTietOptional.get());
            }
            Optional<HangHoaThongSo> hangHoaThongSo2 = Optional.ofNullable(hangHoaThongSoRepo.save(hangHoaThongSo1));
            if (hangHoaThongSo2.isPresent()) {
                response.success();
            }
        } else {
            response.setResult(-1, "Update thông số hàng hóa không thành công");
            return response;
        }
        return response;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            return hangHoaThongSoRepo.delete(id) >= 0;
        } catch (Exception e) {
            return false;
        }
    }
}
