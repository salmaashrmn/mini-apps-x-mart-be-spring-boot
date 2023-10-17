package com.miniapps.xmart.controller;

import com.miniapps.xmart.dto.CustomerDTO;
import com.miniapps.xmart.dto.response.Response;
import com.miniapps.xmart.dto.response.ResponseList;
import com.miniapps.xmart.model.Customer;
import com.miniapps.xmart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<ResponseList<CustomerDTO>> displayCustomer(){
        try{
            List<CustomerDTO> customers = customerService.getAllCustomer();

            ResponseList<CustomerDTO> response = new ResponseList<>();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Sukses");
            response.setData(customers);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e) {
            ResponseList<CustomerDTO> response = new ResponseList<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<Response<CustomerDTO>> detailCustomer(
            @RequestParam(name = "qr_code") String qrCode){
        try{
            CustomerDTO customer = customerService.getDetailCustomer(qrCode);

            Response<CustomerDTO>response = new Response<>();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Sukses");
            response.setData(customer);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e) {
            Response<CustomerDTO> response = new Response<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
