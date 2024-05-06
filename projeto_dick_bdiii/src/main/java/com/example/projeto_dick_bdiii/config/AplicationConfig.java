package com.example.projeto_dick_bdiii.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AplicationConfig {
    
    @Bean
    public ModelMapper napper(){
        return new ModelMapper();
    }
}
