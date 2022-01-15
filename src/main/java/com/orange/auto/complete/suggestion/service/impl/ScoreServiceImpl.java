package com.orange.auto.complete.suggestion.service.impl;

import com.orange.auto.complete.suggestion.domain.RequestPoint;
import com.orange.auto.complete.suggestion.domain.dto.CitySuggestionDto;
import com.orange.auto.complete.suggestion.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {


    @Override
    public List<CitySuggestionDto> setScore(List<CitySuggestionDto> cities, RequestPoint requestPoint) {

        List<CitySuggestionDto> scoredCities = cities;

        if (requestPoint != null) {

            scoredCities.sort(Comparator.comparing(element -> this.getDistance(element, requestPoint)));
            increaseSortedSuggestionsScore(scoredCities);
        }

        scoredCities.sort(Comparator.comparing(element -> element.getCityName().length()));
        increaseSortedSuggestionsScore(scoredCities);

        return scoredCities;
    }

    private void increaseSortedSuggestionsScore(List<CitySuggestionDto> cities) {

        int citiesSize = cities.size();
        double scoreIncrement = 0.5 / citiesSize;

        for (int i = 0; i < citiesSize; i++) {

            var citySuggestion = cities.get(i);

            citySuggestion.setScore(citySuggestion.getScore() + scoreIncrement * (citiesSize - i));
        }
    }

    private Double getDistance(CitySuggestionDto citySuggestionDto, RequestPoint requestPoint) {

        double latDistance = Math.abs(citySuggestionDto.getLatitude() - requestPoint.getLatitude());
        double longDistance = Math.abs(citySuggestionDto.getLongitude() - requestPoint.getLongitude());

        return Math.hypot(latDistance, longDistance);
    }
}

