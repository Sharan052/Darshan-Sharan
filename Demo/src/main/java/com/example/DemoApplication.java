package com.example;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

   @Bean
    ModelMapper modelMapper() {
		
        ModelMapper modelMapper = new ModelMapper();
       
        return modelMapper;
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	

}
  