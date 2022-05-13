package com.gajob.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gajob.enumtype.Department;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // DB의 테이블과 1:1 매핑되는 객체
@Table(name = "user") // 테이블명 user로 지정
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(length = 50, nullable = false, unique = true)
  private String email; //유저아이디

  @Column(length = 100, nullable = false)
  private String password; //유저비밀번호

  @Column(nullable = false)
  private String name; //유저이름

  @Column(nullable = false, name = "nickname", length = 50)
  private String nickname; //닉네임

  @Column(nullable = false)
  private String studentId; //학번

  @Column(nullable = false)
  private String studentEmail; //학교이메일

  @Column
  private String introduction; //소개글

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Department department; //학부

  @Column(name = "activated")
  private boolean activated; //활성화여부

  @ManyToMany
  @JoinTable(
      name = "user_authority",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
      inverseJoinColumns = {
          @JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
  private Set<Authority> authorities;

  //회원의 닉네임과 학부 수정
  public void update(String nickname, Department department, String introduction) {
    this.nickname = nickname;
    this.department = department;
    this.introduction = introduction;
  }

  //비밀번호 수정
  public void passwordUpdate(String password) {
    this.password = password;
  }


}