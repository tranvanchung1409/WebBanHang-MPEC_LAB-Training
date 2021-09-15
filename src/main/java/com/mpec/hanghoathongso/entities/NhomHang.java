package com.mpec.hanghoathongso.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "nhom_hang", schema = "thong_so_ki_thuat")
public class NhomHang implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma_nhom_hang")
    private String maNhomHang;

    @Column(name = "ten_nhom_hang")
    private String tenNhomHang;

    @Column(name = "xoa")
    private Boolean xoa;

    @Transient
    @JsonManagedReference
    private List<HangHoa> hangHoas;
}
