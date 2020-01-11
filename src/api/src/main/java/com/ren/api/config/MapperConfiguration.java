package com.ren.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.modelmapper.ModelMapper;

@Configuration
public class MapperConfiguration {

    @Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}