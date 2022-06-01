package com.gajob.dto.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gajob.entity.posts.PostsScrap;
import com.gajob.enumtype.JobCategory;
import com.gajob.enumtype.PostCategory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class PostsScrapResponseDto {

  private Long id;
  private String title;

  private String content;
  private PostCategory postCategory;
  private JobCategory jobCategory;
  private String writer;

  private int view;
  private String createdDate;

  private String modifiedDate;

  private String nickname;

  @JsonIgnore
  private List<PostsCommentsResponseDto> comments;

  private int commentsCnt;

  private int likes;

  private String scrapDate;
  private Long postId;


  public PostsScrapResponseDto(PostsScrap postsScrap) {
    this.id = postsScrap.getId();
    this.title = postsScrap.getPosts().getTitle();
    this.content = postsScrap.getPosts().getContent();
    this.postCategory = postsScrap.getPosts().getPostCategory();
    this.jobCategory = postsScrap.getPosts().getJobCategory();
    this.writer = postsScrap.getPosts().getWriter();
    this.view = postsScrap.getPosts().getView();
    this.createdDate = postsScrap.getPosts().getCreatedDate();
    this.modifiedDate = postsScrap.getPosts().getModifiedDate();
    this.nickname = postsScrap.getUser().getNickname();
    this.comments = postsScrap.getPosts().getPostsCommentsList().stream()
        .map(PostsCommentsResponseDto::new).collect(
            Collectors.toList());
    this.commentsCnt = comments.size();
    this.likes = postsScrap.getPosts().getLikeList().size();
    this.scrapDate = postsScrap.getCreatedDate();
    this.postId = postsScrap.getPosts().getId();
  }

}
