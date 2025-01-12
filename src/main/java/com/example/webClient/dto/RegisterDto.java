package com.example.webClient.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String phoneNumber;
    private String retailerAccount;
    private String outletId;

}
 