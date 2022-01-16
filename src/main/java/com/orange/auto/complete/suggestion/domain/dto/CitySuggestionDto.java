package com.orange.auto.complete.suggestion.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//TODO API Model
@Builder
@Setter
@Getter
public class CitySuggestionDto {


    String cityName;
    String stateCode;
    String countryCode;
    Double latitude;
    Double longitude;
    Double score;
}
