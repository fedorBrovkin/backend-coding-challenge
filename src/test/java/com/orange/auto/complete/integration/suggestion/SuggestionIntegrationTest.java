package com.orange.auto.complete.integration.suggestion;

import com.orange.auto.complete.city.repository.CityRepository;
import com.orange.auto.complete.integration.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(classes = {TestConfig.class})
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class SuggestionIntegrationTest {

    //MockMvc mockMvc;

    @Autowired
    private CityRepository cityRepository;

    @Test
    @Sql(
            scripts = "/db/script/insert_test_data.sql",
            executionPhase = BEFORE_TEST_METHOD
    )
    @Sql(
            scripts = "/db/script/clean_test_data.sql",
            executionPhase = AFTER_TEST_METHOD
    )
    @DisplayName("Basic success request case.")
    void success(){


        cityRepository.findAll().forEach(element -> System.out.println(element.getName()));
    }
}
