package com.example.webClient.service;

import com.example.webClient.dto.CommitDto;
import com.example.webClient.dto.RegisterDto;
import com.example.webClient.model.RegisterCustomer;

public interface RegisterService {

    String saveToRedis(RegisterDto registerDto);
    String commitData(CommitDto commitDto);
}
