package com.example.webClient.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "code", "data" }) // Enforce the field order
public class ResponseSession<T> {
    private Integer code=200;
    private T data;

    public ResponseSession(T data) {
        this.data = data;
    }
}
