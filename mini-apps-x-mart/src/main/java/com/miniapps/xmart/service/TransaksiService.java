package com.miniapps.xmart.service;

import com.miniapps.xmart.dto.*;
import com.miniapps.xmart.model.Barang;
import com.miniapps.xmart.model.Customer;
import com.miniapps.xmart.model.Transaksi;
import com.miniapps.xmart.model.TransaksiKey;
import com.miniapps.xmart.repository.BarangRepository;
import com.miniapps.xmart.repository.CustomerRepository;
import com.miniapps.xmart.repository.TransaksiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransaksiService {
    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BarangRepository barangRepository;

    @Transactional
    public TransaksiBody saveTransaksi(TransaksiBody request){
        try{
            Customer customer = customerRepository.findByQrCode(request.getQrCode());
            if (customer == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data not found");
            }

            for (BarangTransaksi barangs : request.getBarang()) {
                System.out.println(barangs.getRfid());
                Barang barang = barangRepository.findByRfid(barangs.getRfid());
                if (barang == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data not found");
                }

                Transaksi transaksi = new Transaksi();
                transaksi.setCustomer(customer);
                transaksi.setBarang(barang);
                transaksi.setHargaSatuan(barang.getHargaSatuan());
                transaksi.setJumlah(barangs.getJumlah());
                transaksi.setDate(LocalDateTime.now());

                transaksiRepository.save(transaksi);

            }

            return request;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "gagal" + " on Data: Create Transaction");
        }
    }

    public List<TransaksiDTO> getAllTransaksi() {
        try{
            List<Transaksi> transaksiList = transaksiRepository.findAll();
            if(transaksiList==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data Kosong");
            }
            return transaksiList.stream().map(this::mapToTransaksiDTO).collect(Collectors.toList());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage() + " on Data: Customer");
        }

    }

    private TransaksiDTO mapToTransaksiDTO(Transaksi transaksi) {
        TransaksiDTO dto = new TransaksiDTO();
        dto.setQrCode(transaksi.getCustomer().getQrCode());
        dto.setRfid(transaksi.getBarang().getRfid());
        dto.setHargaSatuan(transaksi.getHargaSatuan());
        dto.setJumlah(transaksi.getJumlah());
        dto.setDate(transaksi.getDate());

        return dto;
    }
}
