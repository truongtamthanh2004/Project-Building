package com.javaweb.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	@Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
		return new ModelMapper();
    }
}
