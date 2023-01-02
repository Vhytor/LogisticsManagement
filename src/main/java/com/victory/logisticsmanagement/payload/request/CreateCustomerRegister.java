package com.victory.logisticsmanagement.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCustomerRegister {
    private String firstName;
    private String lastName;
    private String passWord;
    private String email;
}
