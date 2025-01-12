package com.example.webClient.service.imp;

import com.example.webClient.configs.GetURL;
import com.example.webClient.service.AccessToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
@Service
public class AccessTokenImp implements AccessToken {
    WebClient client = WebClient.create();

    @Override
    public String getAccessToken() {
        log.info("Get access token===========================");
        String url = GetURL.getBaseURL() + "/oauth/token";
        log.info("=======BashURL======= {}", url);
        // Request parameters
        Map<String, String> requestBody = Map.of(
                "username", "013208231",
                "password", "nJXA2MooHMtqzVfDnS7crQ==",
                "client_id", "mobileapps_test",
                "client_secret", "202122232425262728292a2b2c2d2e2f",
                "device_id", "1656D0C7-0792-4A44-A2D1-EC80B14ED789",
                "grant_type", "password", "application_id",
                "MWX_PORTAL_APP");
        try {
            String response = client.post().uri(url).contentType(MediaType.APPLICATION_FORM_URLENCODED) // Set content type
                    .bodyValue(encodeForm(requestBody)) // Encode form data
                    .retrieve().bodyToMono(String.class) // Extract the response as a String
                    .block();

            log.info("Response: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Error occurred while fetching access token", e);
            return null;
        }
    }

    // Helper method to encode parameters for x-www-form-urlencoded
    private String encodeForm(Map<String, String> formData) {
        return formData.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).reduce((param1, param2) -> param1 + "&" + param2).orElse("");
    }
}
