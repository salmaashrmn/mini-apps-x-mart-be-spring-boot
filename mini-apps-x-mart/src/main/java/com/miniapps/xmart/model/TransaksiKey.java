package com.miniapps.xmart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransaksiKey implements Serializable {
    @Column(name="qr_code")
    private String qrCode;

    @Column(name="rfid")
    private String rfid;
}
