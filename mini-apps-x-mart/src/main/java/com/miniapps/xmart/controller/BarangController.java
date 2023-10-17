package com.miniapps.xmart.controller;

import com.miniapps.xmart.dto.BarangDTO;
import com.miniapps.xmart.dto.CustomerDTO;
import com.miniapps.xmart.dto.response.Response;
import com.miniapps.xmart.dto.response.ResponseList;
import com.miniapps.xmart.model.Barang;
import com.miniapps.xmart.model.Customer;
import com.miniapps.xmart.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/barang")
public class BarangController {
    @Autowired
    private BarangService barangService;

    @GetMapping("/")
    public ResponseEntity<ResponseList<BarangDTO>> displayBarang(){
        try{
            List<BarangDTO> barangs = barangService.getAllBarang();

            ResponseList<BarangDTO> response = new ResponseList<>();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Sukses");
            response.setData(barangs);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e) {
            ResponseList<BarangDTO> response = new ResponseList<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<Response<Barang>> detailBarang(
            @RequestParam(name = "rfid") String rfid){
        try{
            Barang barang = barangService.getDetailBarang(rfid);

            Response<Barang>response = new Response<>();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Sukses");
            response.setData(barang);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e) {
            Response<Barang> response = new Response<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
