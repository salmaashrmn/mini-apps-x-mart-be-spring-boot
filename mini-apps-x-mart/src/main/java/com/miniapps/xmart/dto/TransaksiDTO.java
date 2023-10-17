package com.miniapps.xmart.dto;

import com.miniapps.xmart.model.Barang;
import com.miniapps.xmart.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiDTO {
    private String qrCode;
    private String rfid;
    private Integer hargaSatuan;
    private Integer jumlah;
    private LocalDateTime date;
}
