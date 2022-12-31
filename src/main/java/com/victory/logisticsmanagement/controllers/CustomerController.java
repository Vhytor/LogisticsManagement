package com.victory.logisticsmanagement.controllers;

import com.victory.logisticsmanagement.data.model.Customer;
import com.victory.logisticsmanagement.payload.request.CreateCustomerRegister;
import com.victory.logisticsmanagement.payload.response.CreateCustomerResponse;
import com.victory.logisticsmanagement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> customerRegister(@RequestBody CreateCustomerRegister createCustomerRegister){
           CreateCustomerResponse customerRegister = customerService.customerRegister(createCustomerRegister);
        return new ResponseEntity<>(customerRegister, HttpStatus.CREATED);
    }
    @GetMapping("/customer")
    public ResponseEntity<?> getCustomerByEmail(@RequestBody String email){
        Customer customer = customerService.getCustomerByEmail(email);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @DeleteMapping("/deletecustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
