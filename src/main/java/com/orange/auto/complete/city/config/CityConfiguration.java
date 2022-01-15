package com.orange.auto.complete.city.config;

import com.orange.auto.complete.city.mapper.CityMapper;
import com.orange.auto.complete.city.repository.CityRepository;
import com.orange.auto.complete.city.service.CityService;
import com.orange.auto.complete.city.service.impl.CityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.format.DateTimeFormatter;

@Configuration
public class CityConfiguration {


    @Bean
    public DateTimeFormatter dateTimeFormatter(){

        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    @Bean
    public CityMapper cityMapper(DateTimeFormatter dateTimeFormatter){

        return new CityMapper(dateTimeFormatter);
    }

    @Bean
    public CityService cityService(CityRepository cityRepository, CityMapper cityMapper){

        return new CityServiceImpl(cityRepository, cityMapper);
    }
}
