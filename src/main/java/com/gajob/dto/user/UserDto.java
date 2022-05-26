package com.gajob.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gajob.enumtype.Department;
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
public class UserDto {

  @NotNull
  @Size(min = 3, max = 50)
  private String email;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @NotNull
  @Size(min = 3, max = 100)
  private String password;

  @NotNull
  @Size(min = 3, max = 50)
  private String name;

  @NotNull
  @Size(min = 3, max = 50)
  private String nickname;

  @NotNull
  @Size(min = 3, max = 50)
  private String studentId;

  @NotNull
  @Size(min = 3, max = 50)
  private String studentEmail;

  @NotNull
  private Department department;

  private String introduction;

  private String profileFilePath;


}
