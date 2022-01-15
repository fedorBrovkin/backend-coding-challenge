package com.orange.auto.complete.suggestion.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Builder
@Setter
@Getter
public class CitySuggestionModel {

    String name;
    String latitude;
    String longitude;
    BigDecimal score;
}
