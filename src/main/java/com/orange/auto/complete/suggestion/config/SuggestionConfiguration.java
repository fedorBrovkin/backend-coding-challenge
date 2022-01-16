package com.orange.auto.complete.suggestion.config;

import com.orange.auto.complete.city.service.CityService;
import com.orange.auto.complete.suggestion.mapper.CitySuggestionMapper;
import com.orange.auto.complete.suggestion.poperties.SuggestionProperties;
import com.orange.auto.complete.suggestion.service.ScoreService;
import com.orange.auto.complete.suggestion.service.SuggestionService;
import com.orange.auto.complete.suggestion.service.impl.ScoreServiceImpl;
import com.orange.auto.complete.suggestion.service.impl.SuggestionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SuggestionConfiguration {

    @Bean
    public CitySuggestionMapper suggestionMapper() {

        return new CitySuggestionMapper();
    }

    @Bean
    public ScoreService scoreService() {

        return new ScoreServiceImpl();
    }

    @Bean
    public SuggestionService suggestionService(
            CityService cityService,
            ScoreService scoreService,
            CitySuggestionMapper citySuggestionMapper,
            SuggestionProperties suggestionProperties
    ) {

        Integer limit = suggestionProperties.getLimit();

        if(limit<1){

            throw new IllegalArgumentException("Suggestion limit parameter value is less them 1.");
        }

        return new SuggestionServiceImpl(cityService, scoreService, citySuggestionMapper, suggestionProperties);
    }
}
