package com.orange.auto.complete.suggestion.resource;

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

    @GetMapping("/suggestions")
    public ResponseEntity<SuggestionModel> getSuggestion(
            @RequestParam("q") String query,
            @RequestParam(value = "latitude", required = false) String latitude,
            @RequestParam(value = "longitude", required = false) String longitude
    ) {

        return ResponseEntity.ok().body(suggestionService.getSuggestions(query, latitude, longitude));
    }
}
