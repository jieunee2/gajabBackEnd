package com.gajob.entity.posts;

import com.gajob.entity.user.User;
import com.gajob.enumtype.Category;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Posts extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long id;

  @Column(length = 100, nullable = false)
  private String title; //제목

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content; //내용

  @Column
  private String writer; //글쓴이

  @Column(columnDefinition = "integer default 0") // 초기 0으로 세팅
  private int view; //조회수

  @Column
  @Enumerated(EnumType.STRING)
  private Category category; //카테고리

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  //게시글이 삭제될 경우 댓글도 삭제되어야 하기 때문에 CascadeType.REMOVE 를 사용
  @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  private List<Comments> commentsList;

  //게시글 수정
  public void update(String title, String content, Category category) {
    this.title = title;
    this.content = content;
    this.category = category;

  }


}
