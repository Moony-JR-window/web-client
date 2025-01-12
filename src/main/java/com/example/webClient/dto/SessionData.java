package com.example.webClient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionData {
    private String session;

    public SessionData(String session) {
        this.session = session;
    }
}
