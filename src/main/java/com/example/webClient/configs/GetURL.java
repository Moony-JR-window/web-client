package com.example.webClient.configs;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetURL {

    private static final Dotenv dotenv = Dotenv.configure()
            .directory("./src/main/resources") // Adjust to your folder containing .env files
            .filename(".env.development")     // Explicitly specify the .env file
            .load();

    public static String getBaseURL() {
        log.info("Fetching Base URL from environment file");
        return dotenv.get("URL", "https://default.url.com"); // Fallback to a default URL if not found
    }
}
