package com.orange.auto.complete.common.constants;

public interface Api {

    String VERSION = "v1";
    String API = "api";
    String DELIMITER = "/";
    String SERVICE_NAME = "cities";
    String API_PATH = DELIMITER + API + DELIMITER + VERSION + DELIMITER +SERVICE_NAME;

    interface Suggestion{

        String QUERY_PARAMETER_NAME = "q";
        String LATITUDE_PARAMETER_NAME = "latitude";
        String LONGITUDE_PARAMETER_NAME = "longitude";

        String PART = "suggestions";
        String URL = API_PATH + DELIMITER + PART;
    }
}
