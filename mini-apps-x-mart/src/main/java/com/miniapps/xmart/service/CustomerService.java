package com.miniapps.xmart.service;

import com.miniapps.xmart.dto.CustomerDTO;
import com.miniapps.xmart.dto.TransaksiDTO;
import com.miniapps.xmart.model.Customer;
import com.miniapps.xmart.model.Transaksi;
import com.miniapps.xmart.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomer() {
        try{
            List<Customer> customers = customerRepository.findAll();
            if(customers==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data Kosong");
            }
            return customers.stream().map(this::mapToCustomerDTO).collect(Collectors.toList());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage() + " on Data: Customer");
        }

    }

    private CustomerDTO mapToCustomerDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setQrCode(customer.getQrCode());
        dto.setNama(customer.getNama());
        dto.setWallet(customer.getWallet());
        return dto;
    }

    public CustomerDTO getDetailCustomer(String qrCode){
        try{
            Customer customer = customerRepository.findByQrCode(qrCode);
            System.out.println(customer.getNama());
            if(customer==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data Kosong");
            }
            List<Transaksi> transaksiList = customer.getTransaksiList();
            if(transaksiList==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data Kosong");
            }
            System.out.println(transaksiList);

            return mapToDetailCustomer(customer);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage() + " on Data: Customer");
        }
    }

    private CustomerDTO mapToDetailCustomer(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setQrCode(customer.getQrCode());
        dto.setNama(customer.getNama());
        dto.setWallet(customer.getWallet());

        List<TransaksiDTO> transaksiDTOS = customer.getTransaksiList().stream()
                .map(transaksi -> new TransaksiDTO(
                        transaksi.getCustomer().getQrCode(),
                        transaksi.getBarang().getRfid(),
                        transaksi.getHargaSatuan(),
                        transaksi.getJumlah(),
                        transaksi.getDate()))
                .collect(Collectors.toList());
        dto.setTransaksiDTO(transaksiDTOS);
        return dto;
    }
}
