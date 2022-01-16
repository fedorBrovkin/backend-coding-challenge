package com.orange.auto.complete.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class CitySuggestionServiceExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Throwable> handleUnexpectedException(HttpServletRequest request, Throwable throwable) {

        Throwable mostSpecificCause = NestedExceptionUtils.getMostSpecificCause(throwable);

        log.error(
                String.format("Unexpected Exception was thrown. Request URI: %s. Message: %s",
                        request.getRequestURL(),
                        mostSpecificCause)
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RuntimeException(mostSpecificCause));
    }

    @ExceptionHandler(NecessaryQueryParameterMissingException.class)
    public ResponseEntity<Throwable> handleMissingParameterException(HttpServletRequest request, Throwable throwable) {

        log.error(String.format(
                "NecessaryQueryParameterMissingException was thrown. Request URI: %s",
                request.getRequestURI())
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RuntimeException(
                                NestedExceptionUtils.getMostSpecificCause(throwable)
                        )
                );
    }
}
