package com.mpec.hanghoathongso.entities.entitiesDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongSoKiThuatDTO {

    private Integer id;

    private String tenThongSo;

    private Integer nhomHangId;

    private Integer nhomThongSoId;
}
