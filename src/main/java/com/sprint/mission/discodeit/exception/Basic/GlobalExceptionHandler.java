package com.sprint.mission.discodeit.exception.Basic;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sprint.mission.discodeit.dto.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DiscodeitException.class)
  public ResponseEntity<ErrorResponse> handleDiscodeit(DiscodeitException e) {
    ErrorCode error = e.getErrorCode();

    ErrorResponse re = ErrorResponse.builder()
            .timestamp(e.getTimestamp())
            .code(error.name()).message(error.getMessage())
            .details(e.getDetails())
            .exceptionType(e.getClass().getSimpleName())
            .status(error.getStatus())
            .build();

    return ResponseEntity
        .status(error.getStatus())
        .body(re);
  }
}
