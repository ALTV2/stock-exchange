package com.tveritin.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class GeneralConfig {
}
