package com.miniapps.xmart.service;

import com.miniapps.xmart.dto.BarangDTO;
import com.miniapps.xmart.model.Barang;
import com.miniapps.xmart.model.Customer;
import com.miniapps.xmart.model.Transaksi;
import com.miniapps.xmart.repository.BarangRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BarangService {
    @Autowired
    private BarangRepository barangRepository;

    public List<BarangDTO> getAllBarang() {
        try{
            List<Barang> barangs = barangRepository.findAll();
            if(barangs==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data Kosong");
            }
            return barangs.stream().map(this::mapToBarangDTO).collect(Collectors.toList());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage() + " on Data: Customer");
        }

    }

    private BarangDTO mapToBarangDTO(Barang barang) {
        BarangDTO dto = new BarangDTO();
        dto.setRfid(barang.getRfid());
        dto.setNamaBarang(barang.getNamaBarang());
        dto.setHargaSatuan(barang.getHargaSatuan());

        return dto;
    }

    public Barang getDetailBarang(String rfid){
        try{
            Barang barang = barangRepository.findByRfid(rfid);
            System.out.println(barang.getNamaBarang());
            if(barang==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data Kosong");
            }
//            List<Transaksi> transaksiList = barang.getTransaksiList();
//            if(transaksiList==null){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data Kosong");
//            }
//            System.out.println(transaksiList);
            return barang;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage() + " on Data: Barang");
        }
    }
}
