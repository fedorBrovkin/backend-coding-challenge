package com.orange.auto.complete.city.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
public class CityDto {

    Integer geonameId;       //  : integer id of record in geonames database
    String name;            //  : name of geographical point (utf8) varchar(200)
    String asciiName;       //  : name of geographical point in plain ascii characters, varchar(200)

    Set<String> alternateNames;   // : alternatenames, comma separated varchar(5000)
    //TODO Best format
    Double latitude;        //  : latitude in decimal degrees (wgs84)
    //TODO Best format
    Double longitude;        // : longitude in decimal degrees (wgs84)

    String featureClass;    // : see http://www.geonames.org/export/codes.html, char(1)
    String featureCode;     // : see http://www.geonames.org/export/codes.html, varchar(10)
    String countryCode;     // : ISO-3166 2-letter country code, 2 characters
    String cc2;               //: alternate country codes, comma separated, ISO-3166 2-letter country code, 60 characters
    String admin1Code;       //: fipscode (subject to change to iso code), see exceptions below, see file admin1Codes.txt for display
    //names of this code varchar(20)
    String admin2Code;       //: code for the second administrative division, a county in the US, see file admin2Codes.txt; varchar(80)
    String admin3Code;       //: code for third level administrative division, varchar(20)
    String admin4Code;       //: code for fourth level administrative division, varchar(20)

    Long population;        //: bigint (8 byte int)
    Integer elevation;         //: in meters, integer
    Integer dem;             //: digital elevation model, srtm3 or gtopo30, average elevation of 3''x3'' (ca 90mx90m) or 30''x30''(ca 900mx900m) area in meters, integer. srtm processed by cgiar/ciat.
    String timezone;       //   : the timezone id (see file timeZone.txt) varchar(40)
    LocalDate modificationDate; //: date of last modification in yyyy-MM-dd format
}
