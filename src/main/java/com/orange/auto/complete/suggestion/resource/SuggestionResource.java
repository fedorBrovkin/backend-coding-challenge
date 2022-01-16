package com.orange.auto.complete.suggestion.resource;

import com.orange.auto.complete.common.constants.Api;
import com.orange.auto.complete.suggestion.domain.model.SuggestionModel;
import com.orange.auto.complete.suggestion.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuggestionResource {

    private final SuggestionService suggestionService;

    @GetMapping(Api.Suggestion.URL)
    public ResponseEntity<SuggestionModel> getSuggestion(
            @RequestParam(Api.Suggestion.QUERY_PARAMETER_NAME)
                    String query,
            @RequestParam(value = Api.Suggestion.LATITUDE_PARAMETER_NAME, required = false)
                    String latitude,
            @RequestParam(value = Api.Suggestion.LONGITUDE_PARAMETER_NAME, required = false)
                    String longitude
    ) {

        return ResponseEntity.ok().body(suggestionService.getSuggestions(query, latitude, longitude));
    }
}
