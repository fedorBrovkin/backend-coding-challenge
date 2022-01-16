package com.orange.auto.complete.unit.city.service;

import com.orange.auto.complete.city.domain.dto.CityDto;
import com.orange.auto.complete.city.domain.entity.CityEntity;
import com.orange.auto.complete.city.mapper.CityMapper;
import com.orange.auto.complete.city.repository.CityRepository;
import com.orange.auto.complete.city.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CitiServiceTest {

    @Mock
    CityMapper cityMapper;
    @Mock
    CityRepository cityRepository;

    @InjectMocks
    CityServiceImpl cityService;

    @Test
    @DisplayName("Basic success test case")
    void findAllBySequenceTest() throws Exception {
        //given
        String testSequence = "test";
        CityEntity testEntity = CityEntity.builder()
                .id(1L)
                .geonameId(4455)
                .name(testSequence)
                .build();
        CityDto testDto = CityDto.builder()
                .name(testSequence)
                .geonameId(4455)
                .build();

        given(
                cityRepository
                        .findAllByAsciiNameOrNameStartsWithOrAlternativeNameContainsInUpperCase(
                                testSequence.toUpperCase())
        ).willReturn(Set.of(testEntity));
        given(cityMapper.toDto(testEntity)).willReturn(testDto);

        //when
        List<CityDto> actual = cityService.findAllBySequence(testSequence);

        //then
        Assertions.assertNotNull(actual);
    }

    @Test
    @DisplayName("Empty or null sequence search return empty list")
    void findAllBySequenceNullSeqTest() throws Exception {
        //given
        String testSequence = null;

        //when
        List<CityDto> actual = cityService.findAllBySequence(testSequence);

        //then
        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual.isEmpty());
        Mockito.verifyNoInteractions(cityMapper);
        Mockito.verifyNoInteractions(cityRepository);
    }
}
