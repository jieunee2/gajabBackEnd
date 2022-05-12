package com.gajob.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class JwtResponseDto {

  private String token;
  private String nickname;

  private String email;

  public JwtResponseDto(String token, String nickname, String email) {
    this.token = token;
    this.nickname = nickname;
    this.email = email;
  }

}