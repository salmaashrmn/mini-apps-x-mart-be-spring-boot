package com.miniapps.xmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "barang", schema = "public")
public class Barang {
    @Id
    @Column(name = "rfid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String rfid;

    @Column(name = "nama_barang")
    private String namaBarang;

    @Column(name = "harga_satuan")
    private Integer hargaSatuan;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transaksi> transaksiList;
}
