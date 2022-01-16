package com.orange.auto.complete.integration.suggestion;

import com.orange.auto.complete.city.repository.CityRepository;
import com.orange.auto.complete.common.constants.Api;
import com.orange.auto.complete.integration.config.TestConfig;
import com.orange.auto.complete.utils.TestDataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.orange.auto.complete.common.constants.Api.Suggestion.LONGITUDE_PARAMETER_NAME;
import static com.orange.auto.complete.utils.TestDataUtils.Request.LATITUDE_PARAMETER_VALUE;
import static com.orange.auto.complete.utils.TestDataUtils.Request.LONGITUDE_PARAMETER_VALUE;
import static com.orange.auto.complete.utils.TestDataUtils.Request.QUERY_PARAMETER_VALUE;
import static com.orange.auto.complete.utils.TestDataUtils.Response.EMPTY_SUCCESS_RESPONSE;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {TestConfig.class})
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class SuggestionIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CityRepository cityRepository;

    @Test
    @Sql(scripts = "/db/script/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/script/clean_test_data.sql", executionPhase = AFTER_TEST_METHOD)
    @DisplayName("Success request[200] case with all parameters.")
    void success_request_with_all_parameters() throws Exception {

        //when
        mockMvc.perform(
                        get(
                                String.format("%s?%s=%s&%s=%s&%s=%s",
                                        Api.Suggestion.URL,
                                        Api.Suggestion.QUERY_PARAMETER_NAME,
                                        QUERY_PARAMETER_VALUE,
                                        Api.Suggestion.LATITUDE_PARAMETER_NAME,
                                        LATITUDE_PARAMETER_VALUE,
                                        LONGITUDE_PARAMETER_NAME,
                                        LONGITUDE_PARAMETER_VALUE
                                )
                        )
                )
                //then
                .andExpect(status().isOk())
                .andExpect(
                        (elm) -> elm.getResponse().getContentAsString()
                                .contains(TestDataUtils.COLLINGS_CITY_NAME)
                )
                .andExpect(
                        (elm) -> elm.getResponse().getContentAsString()
                                .contains(TestDataUtils.COLLINGSWOOD_CITY_NAME)
                )
                .andExpect(
                        (elm) -> elm.getResponse().getContentAsString()
                                .contains(TestDataUtils.COLLINGSWAOD_CITY_NAME)
                )
                .andExpect(
                        (elm) -> Assertions.assertFalse(
                                elm.getResponse().getContentAsString()
                                        .contains(TestDataUtils.CALLINGSWOOD_CITY_NAME)
                        )

                );
    }

    @Test
    @Sql(scripts = "/db/script/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/script/clean_test_data.sql", executionPhase = AFTER_TEST_METHOD)
    @DisplayName("Success request[200] case, but no match.")
    void success_request_with_all_parameters_but_nothing_was_found() throws Exception {

        //given
        String missedMatchQueryParameterValue = "A_long_time_ago_in_a_galaxy_far,_far_away";

        //when
        mockMvc.perform(
                        get(
                                String.format("%s?%s=%s&%s=%s&%s=%s",
                                        Api.Suggestion.URL,
                                        Api.Suggestion.QUERY_PARAMETER_NAME,
                                        missedMatchQueryParameterValue,
                                        Api.Suggestion.LATITUDE_PARAMETER_NAME,
                                        LATITUDE_PARAMETER_VALUE,
                                        LONGITUDE_PARAMETER_NAME,
                                        LONGITUDE_PARAMETER_VALUE
                                )
                        )
                )
                //then
                .andExpect(status().isOk())
                .andExpect(
                        elm ->
                                Assertions.assertTrue(cityRepository.findAll().size() > 0))
                .andExpect(
                        (elm) -> Assertions.assertEquals(
                                EMPTY_SUCCESS_RESPONSE,
                                elm.getResponse().getContentAsString()
                        )
                );
    }

    @Test
    @Sql(scripts = "/db/script/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/script/clean_test_data.sql", executionPhase = AFTER_TEST_METHOD)
    @DisplayName("Success request[200] case with no latitude in parameters..")
    void success_request_with_q_and_long() throws Exception {


        //when
        mockMvc.perform(
                        get(
                                String.format("%s?%s=%s&%s=%s",
                                        Api.Suggestion.URL,
                                        Api.Suggestion.QUERY_PARAMETER_NAME,
                                        QUERY_PARAMETER_VALUE,
                                        LONGITUDE_PARAMETER_NAME,
                                        LONGITUDE_PARAMETER_VALUE
                                )
                        )
                )
                //then
                .andExpect(status().isOk())
                .andExpect(
                        (elm) -> elm.getResponse().getContentAsString()
                                .contains(TestDataUtils.COLLINGS_CITY_NAME)
                )
                .andExpect(
                        (elm) -> Assertions.assertFalse(
                                elm.getResponse().getContentAsString()
                                        .contains(TestDataUtils.CALLINGSWOOD_CITY_NAME)
                        )

                );
    }

    @Test
    @Sql(scripts = "/db/script/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/script/clean_test_data.sql", executionPhase = AFTER_TEST_METHOD)
    @DisplayName("Success request[200] case with empty latitude parameter.")
    void success_request_with_empty_latitude_parameter() throws Exception {

        //when
        mockMvc.perform(
                        get(
                                String.format("%s?%s=%s&%s=&%s=%s",
                                        Api.Suggestion.URL,
                                        Api.Suggestion.QUERY_PARAMETER_NAME,
                                        QUERY_PARAMETER_VALUE,
                                        Api.Suggestion.LATITUDE_PARAMETER_NAME,
                                        LONGITUDE_PARAMETER_NAME,
                                        LONGITUDE_PARAMETER_VALUE
                                )
                        )
                )
                //then
                .andExpect(status().isOk())
                .andExpect(
                        (elm) -> elm.getResponse().getContentAsString()
                                .contains(TestDataUtils.COLLINGS_CITY_NAME)
                )
                .andExpect(
                        (elm) -> Assertions.assertFalse(
                                elm.getResponse().getContentAsString()
                                        .contains(TestDataUtils.CALLINGSWOOD_CITY_NAME)
                        )

                );
    }

    @Test
    @Sql(scripts = "/db/script/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "/db/script/clean_test_data.sql", executionPhase = AFTER_TEST_METHOD)
    @DisplayName("Success request[200] case with no longitude in parameters..")
    void success_request_with_q_and_lat() throws Exception {


        //when
        mockMvc.perform(
                        get(
                                String.format("%s?%s=%s&%s=%s&%s=%s",
                                        Api.Suggestion.URL,
                                        Api.Suggestion.QUERY_PARAMETER_NAME,
                                        QUERY_PARAMETER_VALUE,
                                        Api.Suggestion.LATITUDE_PARAMETER_NAME,
                                        LATITUDE_PARAMETER_VALUE,
                                        LONGITUDE_PARAMETER_NAME,
                                        LONGITUDE_PARAMETER_VALUE
                                )
                        )
                )
                //then
                .andExpect(status().isOk())
                .andExpect(
                        (elm) -> elm.getResponse().getContentAsString()
                                .contains(TestDataUtils.COLLINGS_CITY_NAME)
                )
                .andExpect(
                        (elm) -> Assertions.assertFalse(
                                elm.getResponse().getContentAsString()
                                        .contains(TestDataUtils.CALLINGSWOOD_CITY_NAME)
                        )

                );
    }

    @Test
    @DisplayName("BadRequest[400] with empty 'q' parameter.")
    void bad_request_request_with_empty_q() throws Exception {

//when
        mockMvc.perform(
                        get(
                                String.format("%s?%s=&%s=%s",
                                        Api.Suggestion.URL,
                                        Api.Suggestion.QUERY_PARAMETER_NAME,
                                        LONGITUDE_PARAMETER_NAME,
                                        LONGITUDE_PARAMETER_VALUE
                                )
                        )
                )
                //then
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("InternalServerError[500] request with no 'q' parameter.")
    void exception_request_with_no_q() throws Exception {


        mockMvc.perform(
                        get(
                                String.format("%s?%s=%s",
                                        Api.Suggestion.URL,
                                        LONGITUDE_PARAMETER_NAME,
                                        LONGITUDE_PARAMETER_VALUE
                                )
                        )
                )
                //then
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Success request[200] with extra parameter")
    void testHm()throws Exception{

        //given
        String extraParameterName = "extraParameter";

        //when
        mockMvc.perform(
                        get(
                                String.format("%s?%s=%s&%s=&%s=%s&%s=%s",
                                        Api.Suggestion.URL,
                                        Api.Suggestion.QUERY_PARAMETER_NAME,
                                        QUERY_PARAMETER_VALUE,
                                        Api.Suggestion.LATITUDE_PARAMETER_NAME,
                                        LATITUDE_PARAMETER_VALUE,
                                        Api.Suggestion.LONGITUDE_PARAMETER_NAME,
                                        LONGITUDE_PARAMETER_VALUE,
                                        extraParameterName,
                                        "extraValue"
                                )
                        )
                )
                //then
                .andExpect(status().isOk())
                .andExpect(
                        (elm) -> elm.getResponse().getContentAsString()
                                .contains(TestDataUtils.COLLINGS_CITY_NAME)
                )
                .andExpect(
                        (elm) -> Assertions.assertFalse(
                                elm.getResponse().getContentAsString()
                                        .contains(TestDataUtils.CALLINGSWOOD_CITY_NAME)
                        )

                );
    }
}
