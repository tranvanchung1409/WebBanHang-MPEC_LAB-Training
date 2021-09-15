package com.mpec.hanghoathongso.entities.entitiesDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhomThongSoDTO {

    private Integer id;

    private String tenNhomThongSo;

    private Integer hangHoaId;

    private String maNhomThongSo;

}
