package com.miniapps.xmart.controller;

import com.miniapps.xmart.dto.BarangDTO;
import com.miniapps.xmart.dto.CustomerDTO;
import com.miniapps.xmart.dto.TransaksiBody;
import com.miniapps.xmart.dto.TransaksiDTO;
import com.miniapps.xmart.dto.response.Response;
import com.miniapps.xmart.dto.response.ResponseList;
import com.miniapps.xmart.model.Transaksi;
import com.miniapps.xmart.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/transaksi")
public class TransaksiController {
    @Autowired
    private TransaksiService transaksiService;

    @PostMapping("/create")
    public ResponseEntity<Response<TransaksiBody>> createTransaction(@RequestBody @Valid TransaksiBody request){
        try {
            TransaksiBody transaksi = transaksiService.saveTransaksi(request);
            Response<TransaksiBody> response = new Response<>(HttpStatus.CREATED.value(), "Transaksi berhasil dibuat", transaksi);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Response<TransaksiBody> response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseList<TransaksiDTO>> displayAllTransactions() {
        try {
            List<TransaksiDTO> transaksiList = transaksiService.getAllTransaksi();
            ResponseList<TransaksiDTO> response = new ResponseList<>(HttpStatus.OK.value(), "Sukses", transaksiList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseList<TransaksiDTO> response = new ResponseList<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
