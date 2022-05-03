package com.gajob.entity.posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gajob.entity.basetime.TimeEntity;
import com.gajob.entity.user.User;
import com.gajob.enumtype.JobCategory;
import com.gajob.enumtype.PostCategory;
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
public class Posts extends TimeEntity {

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
  private PostCategory postCategory; //게시물 카테고리

  @Column
  @Enumerated(EnumType.STRING)
  private JobCategory jobCategory; //직군별 카테고리

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(columnDefinition = "integer default 0") // 초기 0으로 세팅
  private int likes; //조회수

  @JsonIgnoreProperties({"posts"})
  @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
  private List<PostsLikes> likeList;

  @JsonIgnoreProperties({"posts"})
  @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
  private List<PostsScrap> postsScrapList;

  //게시글이 삭제될 경우 댓글도 삭제되어야 하기 때문에 CascadeType.REMOVE 를 사용
  @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  private List<PostsComments> postsCommentsList;

  //게시글 수정
  public void update(String title, String content, PostCategory postCategory,
      JobCategory jobCategory) {
    this.title = title;
    this.content = content;
    this.postCategory = postCategory;
    this.jobCategory = jobCategory;
  }

  public void likeUpdate(int likes) {
    this.likes = likes;
  }

}
