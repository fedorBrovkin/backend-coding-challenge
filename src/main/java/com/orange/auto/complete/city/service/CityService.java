package com.orange.auto.complete.city.service;

import com.orange.auto.complete.city.domain.dto.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> findAllBySequence(String sequence);
}