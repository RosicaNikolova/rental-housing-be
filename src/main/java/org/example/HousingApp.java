package org.example;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HousingApp {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public static void main(String[] args){SpringApplication.run(HousingApp.class, args);}
}