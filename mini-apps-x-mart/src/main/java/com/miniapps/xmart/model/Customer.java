package com.miniapps.xmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer", schema = "public")
public class Customer {
    @Id
    @Column(name = "qr_code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String qrCode;

    @Column(name = "nama")
    private String nama;

    @Column(name = "wallet")
    private Integer wallet;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transaksi> transaksiList;
}
