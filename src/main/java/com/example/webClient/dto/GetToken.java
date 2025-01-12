package com.example.webClient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private Integer expires_in;
}
