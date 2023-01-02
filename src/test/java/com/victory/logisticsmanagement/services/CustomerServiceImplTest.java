package com.victory.logisticsmanagement.services;

import com.victory.logisticsmanagement.data.model.Customer;
import com.victory.logisticsmanagement.data.repositories.CustomerRepository;
import com.victory.logisticsmanagement.payload.request.CreateCustomerRegister;
import com.victory.logisticsmanagement.payload.response.CreateCustomerResponse;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Builder
@SpringBootTest
class CustomerServiceImplTest {

    private CustomerService customerService;

    private CreateCustomerResponse createCustomerResponse;

    private CreateCustomerRegister createCustomerRegister;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
        createCustomerResponse = new CreateCustomerResponse();

    }
    @Test
    void customerHasBeenCreatedTest(){
      createCustomerRegister = new CreateCustomerRegister("Victor","Love","1234abcd","vicky@gmail.com");
      createCustomerResponse = customerService.customerRegister(createCustomerRegister);
      assertNotNull(createCustomerResponse);



    }
}