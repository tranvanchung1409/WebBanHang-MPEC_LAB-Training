package com.mpec.hanghoathongso.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "thong_so_ki_thuat", schema = "thong_so_ki_thuat")
public class ThongSoKiThuat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ten_thong_so")
    private String tenThongSo;

    @ManyToOne
    @JoinColumn(name = "nhom_hang_id")
    private NhomHang nhomHang;

    @Column(name = "xoa")
    private Boolean xoa;

    @ManyToOne
    @JoinColumn(name = "nhom_thong_so_id")
    private NhomThongSo nhomThongSo;

}
