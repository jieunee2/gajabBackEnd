package com.gajob.handler;

import static com.gajob.enumtype.ErrorCode.DUPLICATE_RESOURCE;

import com.gajob.dto.error.ErrorResponse;
import com.gajob.exception.CustomException;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice //프로젝트 전역에서 발생하는 모든 예외를 잡아줌
public class GlobalExceptionHandler extends
    ResponseEntityExceptionHandler { // 발생한 특정 예외를 잡아서 하나의 메소드에서 공통 처리

  // hibername 관련 에러 처리
  @ExceptionHandler(value = {ConstraintViolationException.class,
      DataIntegrityViolationException.class})
  protected ResponseEntity<ErrorResponse> handleDataException() {
    log.error("handleDataException throw Exception : {}", DUPLICATE_RESOURCE);
    return ErrorResponse.toResponseEntity(DUPLICATE_RESOURCE);
  }

  // 직접 정의한 CustomException 사용
  @ExceptionHandler(value = {CustomException.class})
  protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
    log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
    return ErrorResponse.toResponseEntity(e.getErrorCode());
  }

}
