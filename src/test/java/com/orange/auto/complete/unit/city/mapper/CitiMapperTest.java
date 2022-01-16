package com.orange.auto.complete.unit.city.mapper;

import com.orange.auto.complete.city.domain.dto.CityDto;
import com.orange.auto.complete.city.domain.entity.CityEntity;
import com.orange.auto.complete.city.mapper.CityMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
class CitiMapperTest {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final CityMapper citiMapper = new CityMapper(dateTimeFormatter);

    @Test
    @DisplayName("Success mapping null fields case.")
    void successNullFieldsMappingCase() {

        //given
        CityEntity entity = CityEntity.builder()
                .build();
        //when
        CityDto actual = citiMapper.toDto(entity);

        //then
        Assertions.assertNotNull(actual);
    }
}
