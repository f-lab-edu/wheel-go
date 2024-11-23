package com.wheelgo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.wheelgo.service", "com.wheelgo.repository"})
public class AppConfig {
}
