package com.orange.auto.complete.suggestion.service.impl;

import com.orange.auto.complete.city.service.CityService;
import com.orange.auto.complete.common.exception.NecessaryQueryParameterMissingException;
import com.orange.auto.complete.suggestion.domain.RequestPoint;
import com.orange.auto.complete.suggestion.domain.dto.CitySuggestionDto;
import com.orange.auto.complete.suggestion.domain.model.SuggestionModel;
import com.orange.auto.complete.suggestion.mapper.CitySuggestionMapper;
import com.orange.auto.complete.suggestion.poperties.SuggestionProperties;
import com.orange.auto.complete.suggestion.service.ScoreService;
import com.orange.auto.complete.suggestion.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {

    private final CityService cityService;
    private final ScoreService scoreService;
    private final CitySuggestionMapper citySuggestionMapper;
    private final SuggestionProperties suggestionProperties;

    @Override
    public SuggestionModel getSuggestions(String query, String latitude, String longitude) {

        if (Strings.isBlank(query)) {

            throw new NecessaryQueryParameterMissingException();
        }

        var cities = cityService.findAllBySequence(query)
                .stream()
                .filter(element -> Strings.isNotBlank(element.getName()))
                .filter(element -> Objects.nonNull(element.getLatitude()))
                .filter(element -> Objects.nonNull(element.getLongitude()))
                .map(citySuggestionMapper::toDto)
                .collect(Collectors.toList());

        var sortedCities = getSortedCitySuggestions(cities, latitude, longitude)
                .stream()
                .limit(suggestionProperties.getLimit())
                .map(citySuggestionMapper::toModel)
                .collect(Collectors.toList());

        return new SuggestionModel(sortedCities);
    }

    private List<CitySuggestionDto> getSortedCitySuggestions(List<CitySuggestionDto> cities, String latitude, String longitude) {

        RequestPoint requestPoint = null;

        if (Strings.isNotBlank(latitude) && Strings.isNotBlank(longitude)) {

            try {

                double latitudeDoubleValue = Double.parseDouble(latitude);
                double longitudeDoubleValue = Double.parseDouble(latitude);
                requestPoint = new RequestPoint(latitudeDoubleValue, longitudeDoubleValue);

            } catch (NumberFormatException numberFormatException) {

                log.warn("Exception while latitude, longitude parameters was thrown. Request point set to null.");
            }
        }

        scoreService.setScore(cities, requestPoint);
        cities.sort(Comparator.comparing(element -> -(element.getScore())));

        return cities;
    }
}
