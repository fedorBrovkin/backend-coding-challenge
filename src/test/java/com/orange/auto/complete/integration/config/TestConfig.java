package com.orange.auto.complete.integration.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Setter
@TestConfiguration
@ConfigurationProperties("test.datasource")
public class TestConfig {

    private static final String START = "start";
    private static final String STOP = "stop";

    private String postgresVersion;
    private String username;
    private String password;

    @Bean(initMethod = START, destroyMethod = STOP)
    public JdbcDatabaseContainer<?> jdbcDatabaseContainer() {

        return new PostgreSQLContainer<>(postgresVersion)
                .withUsername(username)
                .withPassword(password);
    }

    @Bean
    public DataSource dataSource(JdbcDatabaseContainer<?> jdbcDatabaseContainer) {

        System.out.println("NASTROIKA");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcDatabaseContainer.getJdbcUrl());
        hikariConfig.setUsername(jdbcDatabaseContainer.getUsername());
        hikariConfig.setPassword(jdbcDatabaseContainer.getPassword());

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();

        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        return filter;
    }

    @Bean
    public MockMvc mockMvc(
            WebApplicationContext webApplicationContext,
            CharacterEncodingFilter characterEncodingFilter
    ) {

        return MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(characterEncodingFilter)
                .build();
    }

}
