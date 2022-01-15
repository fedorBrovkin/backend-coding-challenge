package com.orange.auto.complete.city.repository;

import com.orange.auto.complete.city.domain.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query(
            value = "SELECT DISTINCT *" +
                    " FROM cities_canada_usa" +
                    " WHERE UPPER(cities_canada_usa.ascii) LIKE ?1%" +
                    " OR UPPER(cities_canada_usa.name) LIKE ?1%" +
                    " OR UPPER(cities_canada_usa.alt_name) LIKE ?1%",
            nativeQuery = true
    )
    Set<CityEntity> findAllByAsciiNameOrNameStartsWithOrAlternativeNameContainsInUpperCase(String string);
}
