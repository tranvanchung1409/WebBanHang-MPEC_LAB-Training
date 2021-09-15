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
@Table(name = "thuong_hieu", schema = "thong_so_ki_thuat")
public class ThuongHieu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ten_thuong_hieu")
    private String tenThuongHieu;

    @Column(name = "xoa")
    private Boolean xoa;

    @Transient
    private List<HangHoa> hangHoas;
}
