package com.mpec.hanghoathongso.entities.entitiesDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HangHoaThongSoDTO {

    private Integer id;

    private Integer hangHoaId;

    private Integer thongSoKiThuatid;

    private Integer thongSoChiTietId;

    private String giaTri;
}
