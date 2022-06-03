package com.gajob.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateDto {

  @NotNull
  @Size(min = 3, max = 100)
  private String oldPassword; // 기존 비밀번호

  @NotNull
  @Size(min = 3, max = 100)
  private String newPassword; // 새로운 비밀번호
}
