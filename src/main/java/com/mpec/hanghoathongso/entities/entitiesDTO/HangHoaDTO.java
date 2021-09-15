package com.mpec.hanghoathongso.entities.entitiesDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HangHoaDTO {

    private Integer id;

    private String ma;

    private String maGiamGia;

    private String moTa;

    private Float phanTramGiamGia;

    private String tenHangHoa;

    private Integer tichDiem;

    private String urlHinhAnh1;

    private String urlHinhAnh2;

    private String urlHinhAnh3;

    private Integer nhomHangId;

    private Integer thuongHieuId;

    private String maVach;
}
