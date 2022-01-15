package com.orange.auto.complete.suggestion.service;

import com.orange.auto.complete.suggestion.domain.dto.CitySuggestionDto;
import com.orange.auto.complete.suggestion.domain.RequestPoint;

import java.util.List;

public interface ScoreService {

    List<CitySuggestionDto> setScore(List<CitySuggestionDto> cities, RequestPoint requestPoint);
}
