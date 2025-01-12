package com.example.webClient.controller;

import com.example.webClient.configs.GetURL;
import com.example.webClient.dto.CheckAccDto;
import com.example.webClient.service.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
@RequestMapping("/v1/checkAcc")
public class CheckAccount {
    final AccessToken token;
    WebClient client = WebClient.create();

    public CheckAccount(AccessToken token) {
        this.token = token;
    }

    @PostMapping()
    public String checkAcc(@RequestBody CheckAccDto checkAccDto) {
        try {

            String url = GetURL.getBaseURL()+ "/api/wingPayMobile/cashier/checkAccount";
            String getToekn = token.getAccessToken();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tokenNode = objectMapper.readTree(getToekn);
            String accessToken = tokenNode.get("access_token").asText(); // Extract only the access_token
            log.info("Access Token: {}", accessToken); // Log only the access token

            return client.post()
                    .uri(url)
                    .bodyValue(checkAccDto) // Directly set the request body
                    .header("Authorization", "Bearer" + accessToken)
                    .header("Content-Type", "application/json")
                    .retrieve()
                    .bodyToMono(String.class) // Retrieve the response as a String
                    .block(); // Block to wait for the response
        } catch (RuntimeException e) {
            throw new RuntimeException("Error");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
