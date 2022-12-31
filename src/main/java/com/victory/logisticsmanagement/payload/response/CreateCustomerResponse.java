package com.victory.logisticsmanagement.payload.response;

import lombok.Data;

@Data
public class CreateCustomerResponse {
        private Long id;
        private String email;
        private String message;
}
