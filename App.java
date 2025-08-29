package com.example.bfh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create("https://bfhldevapigw.healthrx.co.in");
    }

    @Bean
    public ApplicationRunner runner(WebClient client) {
        return args -> {
            // Build request body
            String body = """
                {
                  "name": "Sandeep kumar koli",
                  "regNo": "22BEC0179",
                  "email": "sandeepkoli8962@gmail.com"
                }
                """;

            // Send POST request
            String response = client.post()
                    .uri("/hiring/generateWebhook/JAVA")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Print the raw response JSON
            System.out.println("Response: " + response);
        };
    }
}


