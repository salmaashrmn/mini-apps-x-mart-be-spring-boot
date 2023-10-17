package com.miniapps.xmart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiBody {
    private String qrCode;
    private List<BarangTransaksi> barang;
}
