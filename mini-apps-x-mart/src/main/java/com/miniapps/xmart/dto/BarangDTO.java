package com.miniapps.xmart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarangDTO {
    private String rfid;
    private String namaBarang;
    private Integer hargaSatuan;
}
