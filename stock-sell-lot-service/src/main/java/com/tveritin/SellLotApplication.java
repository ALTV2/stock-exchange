package com.tveritin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableConfigurationProperties
@ConfigurationPropertiesScan
@SpringBootApplication
@EnableDiscoveryClient
public class SellLotApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellLotApplication.class, args);
    }
}