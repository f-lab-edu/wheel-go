package com.wheelgo.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        // Java 8 이상 LocalDateTime 직렬화/역직렬화 지원
        objectMapper.registerModule(new JavaTimeModule());
        // JSON 직렬화 시 가독성을 위한 Pretty Print
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // JSON과 객체가 완전히 매칭되지 않을 경우 무시
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}