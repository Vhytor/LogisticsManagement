package com.victory.logisticsmanagement.services;

import com.victory.logisticsmanagement.data.model.Customer;
import com.victory.logisticsmanagement.exceptions.CustomerNotFound;
import com.victory.logisticsmanagement.payload.request.CreateCustomerRegister;
import com.victory.logisticsmanagement.payload.response.CreateCustomerResponse;
import org.springframework.stereotype.Service;


@Service
public interface CustomerService {
     CreateCustomerResponse customerRegister(CreateCustomerRegister createCustomerRegister);
     Customer getCustomerByEmail(String email) throws CustomerNotFound;
     Customer updateCustomerInfo(String email,CreateCustomerRegister createCustomerRegister);
     void deleteCustomer(Long id);
}
