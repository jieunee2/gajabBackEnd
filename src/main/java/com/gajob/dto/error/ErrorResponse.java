package com.gajob.dto.error;

import com.gajob.enumtype.ErrorCode;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {

  private final LocalDateTime timestamp = LocalDateTime.now();
  private final int status;
  private final String error;
  private final String code;
  private final String message;

  //ErrorCode를 받아서 ResponseEntity<ErrorResponse>로 변환
  public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
    return ResponseEntity
        .status(errorCode.getHttpStatus())
        .body(ErrorResponse.builder()
            .status(errorCode.getHttpStatus().value())
            .error(errorCode.getHttpStatus().name())
            .code(errorCode.name())
            .message(errorCode.getDetail())
            .build()
        );
  }
}