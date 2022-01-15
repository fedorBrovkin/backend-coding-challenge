package com.orange.auto.complete.suggestion.service;

import com.orange.auto.complete.suggestion.domain.model.SuggestionModel;

public interface SuggestionService {

    SuggestionModel getSuggestions(String query, String latitude, String longitude);
}
