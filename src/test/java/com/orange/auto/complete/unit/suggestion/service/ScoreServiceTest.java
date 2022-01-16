package com.orange.auto.complete.unit.suggestion.service;

import com.orange.auto.complete.suggestion.domain.RequestPoint;
import com.orange.auto.complete.suggestion.domain.dto.CitySuggestionDto;
import com.orange.auto.complete.suggestion.service.ScoreService;
import com.orange.auto.complete.suggestion.service.impl.ScoreServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionDtoData.CITY_SUGGESTION_DTO_LIST;
import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionDtoData.CITY_SUGGESTION_DTO_Prague;

@ExtendWith(MockitoExtension.class)
class ScoreServiceTest {


    ScoreService scoreService = new ScoreServiceImpl();

    @Test
    @DisplayName("setScore base success case")
    void baseSetScoreSuccessCase() {

        //given
        RequestPoint requestPoint = new RequestPoint(23.994, -99.444);
        List<CitySuggestionDto> list = new ArrayList<CitySuggestionDto>(CITY_SUGGESTION_DTO_LIST);

        //when
        List<CitySuggestionDto> actual = scoreService.setScore(list, requestPoint);

        //then
        Assertions.assertNotNull(actual);
    }

    @Test
    @DisplayName("setScore algorithm set top score by dist to the closest with lowest symbol Number")
    void setScoreSuccessCase() {

        //given
        RequestPoint requestPoint = new RequestPoint(53.994, 50.444);
        List<CitySuggestionDto> list = new ArrayList<CitySuggestionDto>(CITY_SUGGESTION_DTO_LIST);

        //when
        List<CitySuggestionDto> actual = scoreService.setScore(list, requestPoint);

        //then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(list.get(0).getCityName(), CITY_SUGGESTION_DTO_Prague.getCityName());
    }

    @Test
    @DisplayName("setScore success case with NULL requestPoint and NULL list")
    void baseSetScoreWithNullPoint() {

        //given
        RequestPoint requestPoint = null;
        List list = null;

        //when
        List actual = scoreService.setScore(list, requestPoint);

        //then
        Assertions.assertNull(actual);
    }

    @Test
    @DisplayName("setScore success case with zero requestPoint and emptyList")
    void baseSetScoreWithEmptyPoint() {

        //given
        RequestPoint requestPoint = new RequestPoint(0, 0);
        List list = Collections.emptyList();

        //when
        List actual = scoreService.setScore(list, requestPoint);

        //then
        Assertions.assertTrue(actual.size() == 0);
    }
}
