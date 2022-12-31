package com.victory.logisticsmanagement.payload.request;

import lombok.Data;

@Data
public class CreateCustomerRegister {
    private String firstName;
    private String lastName;
    private String passWord;
    private String email;
}
