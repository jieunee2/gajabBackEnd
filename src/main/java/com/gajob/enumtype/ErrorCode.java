package com.gajob.enumtype;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  /* 400 BAD_REQUEST : 잘못된 요청 */
//  INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),

  /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
//  INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
  BAD_CREDENTIALS(UNAUTHORIZED, "잘못된 아이디 또는 패스워드를 입력 했습니다. 다시 확인해주세요."),
  /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
  USER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),

  /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
  DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),

  /* 500 INTERNAL_SERVER_ERROR : 예외적인 또는 예측하지 못한 에러 */
  DUPLICATE_USER(INTERNAL_SERVER_ERROR, "이미 가입된 유저입니다."),
  DUPLICATE_NICKNAME(INTERNAL_SERVER_ERROR, "중복된 닉네임이 존재합니다."),
  DUPLICATE_STUDENT_ID(INTERNAL_SERVER_ERROR, "중복된 학번이 존재합니다."),
  POST_ID_NOT_EXIST(INTERNAL_SERVER_ERROR, "해당 게시글이 존재하지 않습니다."),
  COMMENT_ID_NOT_EXIST(INTERNAL_SERVER_ERROR, "해당 댓글이 존재하지 않습니다"),
  NO_ACCESS_RIGHTS(INTERNAL_SERVER_ERROR, "접근 권한이 없습니다."),
  COVER_LETTER_ID_NOT_EXIST(INTERNAL_SERVER_ERROR, "해당 자기소개서가 존재하지 않습니다"),
  ITEM_ID_NOT_EXIST(INTERNAL_SERVER_ERROR, "해당 항목이 존재하지 않습니다"),
  INVALID_STUDENT_ID(INTERNAL_SERVER_ERROR, "잘못된 형식의 학번입니다.");

  private final HttpStatus httpStatus;
  private final String detail;

}
