package com.tveritin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWay {
    public static void main(String[] args) {
        SpringApplication.run(GateWay.class, args);

    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}