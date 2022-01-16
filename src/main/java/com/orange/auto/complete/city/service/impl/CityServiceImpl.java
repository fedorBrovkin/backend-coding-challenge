package com.orange.auto.complete.city.service.impl;

import com.orange.auto.complete.city.domain.dto.CityDto;
import com.orange.auto.complete.city.mapper.CityMapper;
import com.orange.auto.complete.city.repository.CityRepository;
import com.orange.auto.complete.city.service.CityService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDto> findAllBySequence(String sequence) {

        if (Strings.isBlank(sequence)) {

            return Collections.emptyList();
        }

        return cityRepository
                .findAllByAsciiNameOrNameStartsWithOrAlternativeNameContainsInUpperCase(sequence.toUpperCase())
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }
}
