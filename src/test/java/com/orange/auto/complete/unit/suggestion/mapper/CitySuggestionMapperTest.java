package com.orange.auto.complete.unit.suggestion.mapper;

import com.orange.auto.complete.city.domain.dto.CityDto;
import com.orange.auto.complete.suggestion.domain.dto.CitySuggestionDto;
import com.orange.auto.complete.suggestion.domain.model.CitySuggestionModel;
import com.orange.auto.complete.suggestion.mapper.CitySuggestionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CitySuggestionMapperTest {

    private final CitySuggestionMapper mapper = new CitySuggestionMapper();

    @Test
    @DisplayName("[toDto] Base success case with empty object")
    void toDtoBaseSuccessTest() {

        //given
        CityDto emptyCityDto = CityDto.builder().build();
        //when
        CitySuggestionDto actual = mapper.toDto(emptyCityDto);
        //then
        Assertions.assertNotNull(actual);
    }

    @Test
    @DisplayName("[toDto] Null input object should return null")
    void toDtoNullSuccessTest() {

        //given
        CityDto nullCityDto = null;
        //when
        CitySuggestionDto actual = mapper.toDto(nullCityDto);
        //then
        Assertions.assertNull(actual);
    }

    @Test
    @DisplayName("[toModel] Base success case with empty object")
    void toModelBaseSuccessTest() {

        //given
        CitySuggestionDto emptyCityDto = CitySuggestionDto.builder().score(0.0).build();
        //when
        CitySuggestionModel actual = mapper.toModel(emptyCityDto);
        //then
        Assertions.assertNotNull(actual);
    }

    @Test
    @DisplayName("[toModel] Null input object should return null")
    void toModelNullSuccessTest() {

        //given
        CitySuggestionDto nullCitySuggestionDto = null;
        //when
        CitySuggestionModel actual = mapper.toModel(nullCitySuggestionDto);
        //then
        Assertions.assertNull(actual);
    }
}
