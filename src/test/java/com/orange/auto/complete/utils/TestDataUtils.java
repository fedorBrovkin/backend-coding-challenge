package com.orange.auto.complete.utils;

import com.orange.auto.complete.city.domain.dto.CityDto;
import com.orange.auto.complete.suggestion.domain.dto.CitySuggestionDto;
import com.orange.auto.complete.suggestion.domain.model.CitySuggestionModel;
import com.orange.auto.complete.suggestion.mapper.CitySuggestionMapper;

import java.util.List;

import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionDtoData.CITY_SUGGESTION_DTO_PRAGUE_DE_MA;
import static com.orange.auto.complete.utils.TestDataUtils.CitySuggestionDtoData.CITY_SUGGESTION_DTO_Paguello;

public final class TestDataUtils {

    public final static String COLLINGSWOOD_CITY_NAME = "Collingswood";
    public final static String COLLINGSWAOD_CITY_NAME = "Collingswaod";
    public final static String COLLINGS_CITY_NAME = "Collings";
    public final static String CALLINGSWOOD_CITY_NAME = "Callingswood";

    public final static CitySuggestionMapper citySuggestionMapper = new CitySuggestionMapper();

    //Request and Response data
    public static class Request {

        public final static String QUERY_PARAMETER_VALUE = "Coll";
        public final static String LONGITUDE_PARAMETER_VALUE = "-39.91817";
        public final static String LATITUDE_PARAMETER_VALUE = "39.91817";
    }

    public static class Response {

        public final static String EMPTY_SUCCESS_RESPONSE = "{\"suggestions\":[]}";
    }

    public static class CityDtoData {

        public final static CityDto CITY_DTO_Paguello =
                CityDto.builder()
                        .latitude(23.1)
                        .longitude(-22.66666)
                        .name("Paguello-on-the-Themza")
                        .admin1Code("US")
                        .countryCode("CA")
                        .build();

        public final static CityDto CITY_DTO_PRAGUE_DE_MA =
                CityDto.builder()
                        .latitude(223.1)
                        .longitude(-122.66666)
                        .name("Prague De Massada")
                        .admin1Code("US")
                        .countryCode("US")
                        .build();

        public final static CityDto CITY_DTO_Prague =
                CityDto.builder()
                        .latitude(53.1)
                        .longitude(53.66666)
                        .name("Prague")
                        .admin1Code("US")
                        .countryCode("CA")
                        .build();

        public final static List<CityDto> CITY_DTO_LIST = List.of(
                CITY_DTO_Paguello,
                CITY_DTO_PRAGUE_DE_MA,
                CITY_DTO_Prague
        );
    }

    public static class CitySuggestionDtoData {

        public final static CitySuggestionDto CITY_SUGGESTION_DTO_Paguello =
                CitySuggestionDto.builder()
                        .score(0.0)
                        .latitude(23.1)
                        .longitude(-22.66666)
                        .cityName("Paguello-on-the-Themza")
                        .stateCode("US")
                        .countryCode("CA")
                        .build();

        public final static CitySuggestionDto CITY_SUGGESTION_DTO_PRAGUE_DE_MA =
                CitySuggestionDto.builder()
                        .score(0.0)
                        .latitude(223.1)
                        .longitude(-122.66666)
                        .cityName("Prague De Massada")
                        .stateCode("US")
                        .countryCode("US")
                        .build();

        public final static CitySuggestionDto CITY_SUGGESTION_DTO_Prague =
                CitySuggestionDto.builder()
                        .score(0.0)
                        .latitude(53.1)
                        .longitude(53.66666)
                        .cityName("Prague")
                        .stateCode("US")
                        .countryCode("CA")
                        .build();

        public final static List<CitySuggestionDto> CITY_SUGGESTION_DTO_LIST = List.of(
                CITY_SUGGESTION_DTO_Paguello,
                CITY_SUGGESTION_DTO_PRAGUE_DE_MA,
                CITY_SUGGESTION_DTO_Prague
        );
    }

    public static class CitySuggestionModelData {

        public final static CitySuggestionModel CITY_SUGGESTION_MODEL_Paguello =
                citySuggestionMapper.toModel(CITY_SUGGESTION_DTO_Paguello);

        public final static CitySuggestionModel CITY_SUGGESTION_MODEL_PRAGUE_DE_MA =
                citySuggestionMapper.toModel(CITY_SUGGESTION_DTO_PRAGUE_DE_MA);

        public final static CitySuggestionModel CITY_SUGGESTION_MODEL_Prague =
                citySuggestionMapper.toModel(CitySuggestionDtoData.CITY_SUGGESTION_DTO_Prague);

        public final static List<CitySuggestionModel> CITY_SUGGESTION_DTO_LIST = List.of(
                CITY_SUGGESTION_MODEL_Paguello,
                CITY_SUGGESTION_MODEL_PRAGUE_DE_MA,
                CITY_SUGGESTION_MODEL_Prague
        );
    }
}
