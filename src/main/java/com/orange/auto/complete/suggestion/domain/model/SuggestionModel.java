package com.orange.auto.complete.suggestion.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orange.auto.complete.suggestion.domain.model.CitySuggestionModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SuggestionModel {

    @JsonProperty("suggestions")
    List<CitySuggestionModel> citySuggestions;
}
