package com.orange.auto.complete.suggestion.poperties;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "suggestion")
@RequiredArgsConstructor
public class SuggestionProperties {

    @NotNull
    private final Integer limit;
}
