package com.miniapps.xmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaksi", schema = "public")

public class Transaksi{
    @ManyToOne
    @JoinColumn(name = "rfid")
    private Barang barang;

    @Column(name = "harga_satuan")
    private Integer hargaSatuan;

    @Column(name = "jumlah")
    private Integer jumlah;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "qr_code")
    @JsonIgnore
    private Customer customer;

    @Id
    @Column(name = "id_transaksi" , nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaksi;
}
