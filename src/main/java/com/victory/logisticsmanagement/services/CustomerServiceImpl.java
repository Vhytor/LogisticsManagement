package com.victory.logisticsmanagement.services;

import com.victory.logisticsmanagement.data.model.Customer;
import com.victory.logisticsmanagement.data.repositories.CustomerRepository;
import com.victory.logisticsmanagement.exceptions.CustomerNotFound;
import com.victory.logisticsmanagement.payload.request.CreateCustomerRegister;
import com.victory.logisticsmanagement.payload.response.CreateCustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public CreateCustomerResponse customerRegister(CreateCustomerRegister createCustomerRegister) {
        CreateCustomerResponse createCustomerResponse = new CreateCustomerResponse();
        if (getCustomerByEmail(createCustomerRegister.getEmail())!= null){
            createCustomerResponse.setMessage("Customer already exist");
        } else {
            Customer customer = new Customer();
            customer.setFirstName(createCustomerRegister.getFirstName());
            customer.setLastName(createCustomerRegister.getLastName());
            customer.setEmail(createCustomerRegister.getEmail());
            customer.setPassWord(createCustomerRegister.getPassWord());

            Customer savedCustomer = customerRepository.save(customer);
            createCustomerResponse.setId(savedCustomer.getId());
            createCustomerResponse.setEmail(savedCustomer.getEmail());
            createCustomerResponse.setMessage("Registration Successful");
        }
        return createCustomerResponse;
    }

    @Override
    public Customer getCustomerByEmail(String email) throws CustomerNotFound {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if(customer == null){
            throw new CustomerNotFound("Customer doesn't exist");
        }
        return customer;
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
    }
    public Customer findCustomerById(Long id) throws CustomerNotFound{
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new CustomerNotFound("Customer doesn't exist");
        }
        return customer.get();

    }
}
