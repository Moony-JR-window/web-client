package com.example.webClient.controller;

import com.example.webClient.dto.CommitDto;
import com.example.webClient.dto.RegisterDto;
import com.example.webClient.dto.ResponseSession;
import com.example.webClient.dto.SessionData;
import com.example.webClient.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/register")
public class Register {
    final RegisterService registerService;

    public Register(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/validate")
    public ResponseEntity<ResponseSession<SessionData>> registerValidate(@Valid @RequestBody RegisterDto registerDto) {
        String sesstionCode = registerService.saveToRedis(registerDto);
        return ResponseEntity.ok(new ResponseSession<>(new SessionData(sesstionCode) ));
    }
    @PostMapping("/commit")
    public String registerCommit(@Valid @RequestBody CommitDto commitDto) {
        registerService.commitData(commitDto);
        return "success";
    }


}
