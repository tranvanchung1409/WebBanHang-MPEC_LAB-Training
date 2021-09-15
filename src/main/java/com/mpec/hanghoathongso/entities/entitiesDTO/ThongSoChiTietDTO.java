package com.mpec.hanghoathongso.entities.entitiesDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThongSoChiTietDTO {

    private Integer id;

    private String ten;

    private String giaTri;

    private Integer thongSoKiThuatId;
}
