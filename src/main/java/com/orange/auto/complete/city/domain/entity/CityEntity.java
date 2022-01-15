package com.orange.auto.complete.city.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities_canada_usa")
public class CityEntity {

    @Id
    Long id;

    @Column(name = "geo_names_id")
    Integer geonameId;
    @Column(name = "name")
    String name;
    @Column(name = "ascii")
    String asciiName;
    @Column(name = "alt_name")
    String alternateNames;
    //TODO Best format
    @Column(name = "lat")
    Double latitude;
    //TODO Best format
    @Column(name = "long")
    Double longitude;
    @Column(name = "feat_class")
    String featureClass;
    @Column(name = "feat_code")
    String featureCode;
    @Column(name = "country")
    String countryCode;
    @Column(name = "cc2")
    String cc2;
    @Column(name = "admin1")
    String admin1Code;
    @Column(name = "admin2")
    String admin2Code;
    @Column(name = "admin3")
    String admin3Code;
    @Column(name = "admin4")
    String admin4Code;
    @Column(name = "population")
    Long population;
    @Column(name = "elevation")
    Integer elevation;
    @Column(name = "dem")
    Integer dem;
    @Column(name = "tz")
    String timezone;
    @Column(name = "modified_at")
    String modificationDate;
}
