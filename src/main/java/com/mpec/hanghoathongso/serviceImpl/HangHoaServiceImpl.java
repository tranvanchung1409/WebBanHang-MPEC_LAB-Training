package com.mpec.hanghoathongso.serviceImpl;

import com.mpec.hanghoathongso.entities.HangHoa;
import com.mpec.hanghoathongso.entities.NhomHang;
import com.mpec.hanghoathongso.entities.ThuongHieu;
import com.mpec.hanghoathongso.entities.entitiesDTO.HangHoaDTO;
import com.mpec.hanghoathongso.repository.HangHoaRepo;
import com.mpec.hanghoathongso.repository.NhomHangRepo;
import com.mpec.hanghoathongso.repository.ThuongHieuRepo;
import com.mpec.hanghoathongso.response.MyResponse;
import com.mpec.hanghoathongso.service.HangHoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HangHoaServiceImpl implements HangHoaService {

    @Autowired
    HangHoaRepo hangHoaRepo;

    @Autowired
    NhomHangRepo nhomHangRepo;

    @Autowired
    ThuongHieuRepo thuongHieuRepo;

    @Override
    public Page<HangHoa> findAll(Pageable pageable) {
        try {
            return hangHoaRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoa> findById(Integer id) {
        try {
            return hangHoaRepo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<HangHoa> search(String text, Pageable pageable) {
        try {
            return hangHoaRepo.search(text, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public List<HangHoa> findByNhomHangId(Integer nhomHangId) {
        try {
            return hangHoaRepo.findByNhomHangId(nhomHangId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<HangHoa> findByThuongHieuId(Integer thuongHieuId) {
        try {
            return hangHoaRepo.findByThuongHieuId(thuongHieuId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MyResponse save(HangHoaDTO hangHoa) {
        MyResponse response = new MyResponse();

        Optional<NhomHang> nhomHangOptional = nhomHangRepo.findById(hangHoa.getNhomHangId());
        if (nhomHangOptional.isEmpty()) {
            response.setResult(-1, "Nhóm hàng không tồn tại");
            return response;
        }
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepo.findById(hangHoa.getThuongHieuId());
        if (thuongHieuOptional.isEmpty()) {
            response.setResult(-1, "Thương hiệu không tồn tại");
            return response;
        } else {
            HangHoa hangHoa1 = new HangHoa();
            hangHoa1.setMa(hangHoa.getMa());
            hangHoa1.setMaGiamGia(hangHoa.getMaGiamGia());
            hangHoa1.setMoTa(hangHoa.getMoTa());
            hangHoa1.setPhanTramGiamGia(hangHoa.getPhanTramGiamGia());
            hangHoa1.setTenHangHoa(hangHoa.getTenHangHoa());
            hangHoa1.setTichDiem(hangHoa.getTichDiem());
            hangHoa1.setUrlHinhAnh1(hangHoa.getUrlHinhAnh1());
            hangHoa1.setUrlHinhAnh2(hangHoa.getUrlHinhAnh2());
            hangHoa1.setUrlHinhAnh3(hangHoa.getUrlHinhAnh3());
            hangHoa1.setXoa(false);
            hangHoa1.setNhomHang(nhomHangOptional.get());
            hangHoa1.setThuongHieu(thuongHieuOptional.get());
            hangHoa1.setMaVach(hangHoa.getMaVach());
            HangHoa hangHoa2 = hangHoaRepo.save(hangHoa1);
            if (hangHoa2 == null) {
                response.setResult(-1, "Lưu hàng hóa không thành công");
                return response;
            } else {
                response.success();
            }
            return response;
        }
    }

    @Override
    public MyResponse update(HangHoaDTO hangHoa) {
        MyResponse response = new MyResponse();

        Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(hangHoa.getId());
        if (hangHoaOptional.isPresent()) {
            HangHoa hangHoa1 = hangHoaOptional.get();
            if (hangHoa.getMa() != null) {
                hangHoa1.setMa(hangHoa.getMa());
            }
            if (hangHoa.getMaGiamGia() != null) {
                hangHoa1.setMaGiamGia(hangHoa.getMaGiamGia());
            }
            if (hangHoa.getMoTa() != null) {
                hangHoa1.setMoTa(hangHoa.getMoTa());
            }
            if (hangHoa.getPhanTramGiamGia() != null) {
                hangHoa1.setPhanTramGiamGia(hangHoa.getPhanTramGiamGia());
            }
            if (hangHoa.getTenHangHoa() != null) {
                hangHoa1.setTenHangHoa(hangHoa.getTenHangHoa());
            }
            if (hangHoa.getTichDiem() != null) {
                hangHoa1.setTichDiem(hangHoa.getTichDiem());
            }
            if (hangHoa.getUrlHinhAnh1() != null) {
                hangHoa1.setUrlHinhAnh1(hangHoa.getUrlHinhAnh1());
            }
            if (hangHoa.getUrlHinhAnh2() != null) {
                hangHoa1.setUrlHinhAnh2(hangHoa.getUrlHinhAnh2());
            }
            if (hangHoa.getUrlHinhAnh3() != null) {
                hangHoa1.setUrlHinhAnh3(hangHoa.getUrlHinhAnh3());
            }
            if (hangHoa.getMaVach() != null) {
                hangHoa1.setMaVach(hangHoa.getMaVach());
            }
            if (hangHoa.getNhomHangId() != null) {
                Optional<NhomHang> nhomHangOptional = nhomHangRepo.findById(hangHoa.getNhomHangId());
                if (nhomHangOptional.isPresent()) {
                    hangHoa1.setNhomHang(nhomHangOptional.get());
                } else {
                    response.setResult(-1, "Nhóm hàng không tồn tại");
                    return response;
                }
            }
            if (hangHoa.getThuongHieuId() != null) {
                Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepo.findById(hangHoa.getThuongHieuId());
                if (thuongHieuOptional.isPresent()) {
                    hangHoa1.setThuongHieu(thuongHieuOptional.get());
                } else {
                    response.setResult(-1, "Thương hiệu không tồn tại");
                    return response;
                }
            }
            Optional<HangHoa> hangHoa2 = Optional.ofNullable(hangHoaRepo.save(hangHoa1));
            if (hangHoa2.isPresent()) {
                response.success();
            }
        } else {
            response.setResult(-1, "Lưu hàng hóa không thành công");
            return response;
        }
        return response;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            return hangHoaRepo.delete(id) >= 0;
        } catch (Exception e) {
            return false;
        }
    }
}
