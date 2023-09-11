package com.victory.logisticsmanagement.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.victory.logisticsmanagement.data.model.Customer;
import com.victory.logisticsmanagement.data.repositories.CustomerRepository;
import com.victory.logisticsmanagement.exceptions.CustomerNotFound;
import com.victory.logisticsmanagement.payload.request.CreateCustomerRegister;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    /**
     * Method under test: {@link CustomerServiceImpl#customerRegister(CreateCustomerRegister)}
     */
    @Test
    void testCustomerRegister() {
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(new Customer());
        assertEquals("Customer already exist",
                customerServiceImpl
                        .customerRegister(new CreateCustomerRegister("Jane", "Doe", "Pass Word", "jane.doe@example.org"))
                        .getMessage());
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#customerRegister(CreateCustomerRegister)}
     */
    @Test
    void testCustomerRegister2() {
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(null);
        assertThrows(CustomerNotFound.class, () -> customerServiceImpl
                .customerRegister(new CreateCustomerRegister("Jane", "Doe", "Pass Word", "jane.doe@example.org")));
        verify(customerRepository).findCustomerByEmail((String) any());
    }
//
//    /**
//     * Method under test: {@link CustomerServiceImpl#customerRegister(CreateCustomerRegister)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testCustomerRegister3() {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException: Cannot invoke "com.victory.logisticsmanagement.payload.request.CreateCustomerRegister.getEmail()" because "createCustomerRegister" is null
//        //       at com.victory.logisticsmanagement.services.CustomerServiceImpl.customerRegister(CustomerServiceImpl.java:27)
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(new Customer());
//        customerServiceImpl.customerRegister(null);
//    }

    /**
     * Method under test: {@link CustomerServiceImpl#customerRegister(CreateCustomerRegister)}
     */
    @Test
    void testCustomerRegister4() {
        when(customerRepository.findCustomerByEmail((String) any()))
                .thenThrow(new CustomerNotFound("Not all who wander are lost"));
        assertThrows(CustomerNotFound.class, () -> customerServiceImpl
                .customerRegister(new CreateCustomerRegister("Jane", "Doe", "Pass Word", "jane.doe@example.org")));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getCustomerByEmail(String)}
     */
    @Test
    void testGetCustomerByEmail() throws CustomerNotFound {
        Customer customer = new Customer();
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(customer);
        assertSame(customer, customerServiceImpl.getCustomerByEmail("jane.doe@example.org"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getCustomerByEmail(String)}
     */
    @Test
    void testGetCustomerByEmail2() throws CustomerNotFound {
        when(customerRepository.findCustomerByEmail((String) any())).thenReturn(null);
        assertThrows(CustomerNotFound.class, () -> customerServiceImpl.getCustomerByEmail("jane.doe@example.org"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getCustomerByEmail(String)}
     */
    @Test
    void testGetCustomerByEmail3() throws CustomerNotFound {
        when(customerRepository.findCustomerByEmail((String) any()))
                .thenThrow(new CustomerNotFound("Not all who wander are lost"));
        assertThrows(CustomerNotFound.class, () -> customerServiceImpl.getCustomerByEmail("jane.doe@example.org"));
        verify(customerRepository).findCustomerByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer() {
        doNothing().when(customerRepository).delete((Customer) any());
        when(customerRepository.findById((Long) any())).thenReturn(Optional.of(new Customer()));
        customerServiceImpl.deleteCustomer(123L);
        verify(customerRepository).findById((Long) any());
        verify(customerRepository).delete((Customer) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer2() {
        doThrow(new CustomerNotFound("Not all who wander are lost")).when(customerRepository).delete((Customer) any());
        when(customerRepository.findById((Long) any())).thenReturn(Optional.of(new Customer()));
        assertThrows(CustomerNotFound.class, () -> customerServiceImpl.deleteCustomer(123L));
        verify(customerRepository).findById((Long) any());
        verify(customerRepository).delete((Customer) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer3() {
        doNothing().when(customerRepository).delete((Customer) any());
        when(customerRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(CustomerNotFound.class, () -> customerServiceImpl.deleteCustomer(123L));
        verify(customerRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#findCustomerById(Long)}
     */
    @Test
    void testFindCustomerById() throws CustomerNotFound {
        Customer customer = new Customer();
        when(customerRepository.findById((Long) any())).thenReturn(Optional.of(customer));
        assertSame(customer, customerServiceImpl.findCustomerById(123L));
        verify(customerRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#findCustomerById(Long)}
     */
    @Test
    void testFindCustomerById2() throws CustomerNotFound {
        when(customerRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(CustomerNotFound.class, () -> customerServiceImpl.findCustomerById(123L));
        verify(customerRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#findCustomerById(Long)}
     */
    @Test
    void testFindCustomerById3() throws CustomerNotFound {
        when(customerRepository.findById((Long) any())).thenThrow(new CustomerNotFound("Not all who wander are lost"));
        assertThrows(CustomerNotFound.class, () -> customerServiceImpl.findCustomerById(123L));
        verify(customerRepository).findById((Long) any());
    }
}

