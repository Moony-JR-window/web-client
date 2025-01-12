package com.example.webClient.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RegisterCustomer")
public class RegisterCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
