package com.gajob.entity.posts;

import com.gajob.entity.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class PostsComments {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comments_id")
  private Long id;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String comment; //내용

  @Column(name = "created_date")
  @CreatedDate
  private String createdDate; //작성일

  @Column(name = "modified_date")
  @LastModifiedDate
  private String modifiedDate; //수정일

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "posts_id")
  private Posts posts;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;


  // 댓글 수정
  public void update(String comment) {
    this.comment = comment;
  }

}
