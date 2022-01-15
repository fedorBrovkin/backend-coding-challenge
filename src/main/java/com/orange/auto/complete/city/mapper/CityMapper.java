package com.orange.auto.complete.city.mapper;

import com.orange.auto.complete.city.domain.dto.CityDto;
import com.orange.auto.complete.city.domain.entity.CityEntity;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CityMapper {

    private final DateTimeFormatter dateTimeFormatter;

    public CityDto toDto(CityEntity entity) {

        if (entity == null) {
            return null;
        }

        return CityDto.builder()
                .geonameId(entity.getGeonameId())
                .name(entity.getName())
                .asciiName(entity.getAsciiName())
                .alternateNames(commaSeparatedStringToSet(entity.getAlternateNames()))
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .featureClass(entity.getFeatureClass())
                .featureCode(entity.getFeatureCode())
                .cc2(entity.getCc2())
                .countryCode(entity.getCountryCode())
                .admin1Code(entity.getAdmin1Code())
                .admin2Code(entity.getAdmin2Code())
                .admin3Code(entity.getAdmin3Code())
                .admin4Code(entity.getAdmin4Code())
                .population(entity.getPopulation())
                .elevation(entity.getElevation())
                .dem(entity.getDem())
                .timezone(entity.getTimezone())
                .modificationDate(LocalDate.parse(entity.getModificationDate(), dateTimeFormatter))
                .build();
    }

    private Set<String> commaSeparatedStringToSet(String string) {

        if (string == null) {

            return Collections.emptySet();
        }

        return Arrays.stream(string.split(",", -1)).collect(Collectors.toSet());
    }
}
