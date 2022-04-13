package com.gajob.exception;

import com.gajob.enumtype.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

  private final ErrorCode errorCode;
}
