package com.miniapps.xmart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String qrCode;
    private String nama;
    private Integer wallet;
    private List<TransaksiDTO> transaksiDTO;
}
