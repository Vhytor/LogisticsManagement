package com.victory.logisticsmanagement.services;

import com.victory.logisticsmanagement.data.model.Customer;
import com.victory.logisticsmanagement.payload.request.CreateCustomerRegister;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Builder
class CustomerServiceImplTest {
    private CustomerService customerService;
    private CreateCustomerRegister createCustomerRegister;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl();
        createCustomerRegister = new CreateCustomerRegister();

    }
    @Test
    void customerExistTest(){
        Customer customer = new Customer();
        createCustomerRegister.setFirstName("victor");
        createCustomerRegister.setLastName("love");
        createCustomerRegister.setEmail("fds@gmail.com");
        createCustomerRegister.setPassWord("1234");
        customerService.customerRegister(createCustomerRegister);

    }
}