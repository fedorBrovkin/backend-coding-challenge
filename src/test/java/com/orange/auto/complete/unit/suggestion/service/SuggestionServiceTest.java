package com.orange.auto.complete.unit.suggestion.service;

import com.orange.auto.complete.city.domain.dto.CityDto;
import com.orange.auto.complete.city.service.CityService;
import com.orange.auto.complete.suggestion.domain.dto.CitySuggestionDto;
import com.orange.auto.complete.suggestion.domain.model.SuggestionModel;
import com.orange.auto.complete.suggestion.mapper.CitySuggestionMapper;
import com.orange.auto.complete.suggestion.poperties.SuggestionProperties;
import com.orange.auto.complete.suggestion.service.ScoreService;
import com.orange.auto.complete.suggestion.service.impl.SuggestionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.orange.auto.complete.utils.TestDataUtils.CityDtoData.CITY_DTO_LIST;
import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionDtoData.CITY_SUGGESTION_DTO_LIST;
import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionDtoData.CITY_SUGGESTION_DTO_PRAGUE_DE_MA;
import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionDtoData.CITY_SUGGESTION_DTO_Paguello;
import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionDtoData.CITY_SUGGESTION_DTO_Prague;
import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionModelData.CITY_SUGGESTION_MODEL_PRAGUE_DE_MA;
import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionModelData.CITY_SUGGESTION_MODEL_Paguello;
import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionModelData.CITY_SUGGESTION_MODEL_Prague;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SuggestionServiceTest {

    @Mock
    private CityService cityService;
    @Mock
    private ScoreService scoreService;
    @Mock
    private CitySuggestionMapper citySuggestionMapper;
    @Mock
    private SuggestionProperties suggestionProperties;

    @InjectMocks
    private SuggestionServiceImpl suggestionService;

    @Test
    @DisplayName("Base success case")
    void baseSuccessCase() {

        //given
        String query = "Prag";
        String latitude = "23.4566";
        String longitude = "-33.23131";
        List<CityDto> list = new ArrayList<>(CITY_DTO_LIST);
        List<CitySuggestionDto> scoredList = new ArrayList<>(CITY_SUGGESTION_DTO_LIST);

        scoredList.forEach(elm -> elm.setScore(0.3));


        given(suggestionProperties.getLimit()).willReturn(10);
        given(cityService.findAllBySequence(query)).willReturn(list);
        given(citySuggestionMapper.toDto(any()))
                .willReturn(CITY_SUGGESTION_DTO_Prague)
                .willReturn(CITY_SUGGESTION_DTO_Paguello)
                .willReturn(CITY_SUGGESTION_DTO_PRAGUE_DE_MA);
        given(citySuggestionMapper.toModel(any()))
                .willReturn(CITY_SUGGESTION_MODEL_Paguello)
                .willReturn(CITY_SUGGESTION_MODEL_PRAGUE_DE_MA)
                .willReturn(CITY_SUGGESTION_MODEL_Prague);
        given(scoreService.setScore(any(), any()))
                .willReturn(scoredList);
        //when
        SuggestionModel actual = suggestionService.getSuggestions(query, latitude, longitude);
        //then
        System.out.println(actual.getCitySuggestions().size());
        Assertions.assertNotNull(actual);
    }
}
