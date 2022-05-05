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

  public JwtResponseDto(String token, String nickname) {
    this.token = token;
    this.nickname = nickname;
  }

}