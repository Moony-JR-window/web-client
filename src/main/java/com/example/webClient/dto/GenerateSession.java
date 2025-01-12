package com.example.webClient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateSession {
    private String Session_Id;
    private Integer expires_in ;
}
