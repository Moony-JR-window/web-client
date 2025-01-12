package com.example.webClient.redis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterRedis implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String phoneNumber;
    private String retailerAccount;
    private String linkedOutlets;
    private String outletId;
    private String levelAuthorities;
    private String account;
    private String retailerMasterAccount;
    private String name;
    private String companyId;
    private String image;
}
