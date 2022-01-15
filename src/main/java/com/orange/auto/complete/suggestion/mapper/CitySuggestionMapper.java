package com.orange.auto.complete.suggestion.mapper;

import com.orange.auto.complete.city.domain.dto.CityDto;
import com.orange.auto.complete.suggestion.domain.dto.CitySuggestionDto;
import com.orange.auto.complete.suggestion.domain.model.CitySuggestionModel;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class CitySuggestionMapper {

    private static final String COORDINATES_FORMAT = "%.5f";
    private static final String  USA_COUNTRY_CODE = "US";
    private static final String CANADA_COUNTRY_CODE = "CA";
    private static final String USA_COUNTRY_NAME = "USA";
    private static final String CANADA_COUNTRY_NAME = "Canada";

    public CitySuggestionDto toDto(CityDto dto) {

        if (dto == null) {

            return null;
        }

        return CitySuggestionDto.builder()
                .cityName(dto.getName())
                .stateCode(dto.getAdmin1Code())
                .countyCode(dto.getCountryCode())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .score(0.0)
                .build();
    }

    public CitySuggestionModel dtoToModel(CitySuggestionDto dto){


        return CitySuggestionModel.builder()
                .name(composeFullName(dto))
                .latitude(String.format(COORDINATES_FORMAT,dto.getLatitude()))
                .longitude(String.format(COORDINATES_FORMAT,dto.getLongitude()))
                .score(BigDecimal.valueOf(dto.getScore()).setScale(1, RoundingMode.HALF_DOWN))
                .build();
    }

    private String composeFullName(CitySuggestionDto dto){

        String countyFullName = getFullCountryName(dto.getCountyCode());

        return String.format("%s, %s, %s", dto.getCityName(), dto.getStateCode(), countyFullName);
    }

    private String getFullCountryName(String code){

        switch(code){
            case USA_COUNTRY_CODE:{

                return USA_COUNTRY_NAME;
            }
            case CANADA_COUNTRY_CODE:{

                return CANADA_COUNTRY_NAME;
            }
            default:{

                return code;
            }
        }
    }
}
