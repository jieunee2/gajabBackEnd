package com.gajob.dto.posts;

import com.gajob.entity.posts.PostsComments;
import lombok.Data;

@Data
public class PostsCommentsResponseDto {

  private Long id;
  private String comment;

  private Boolean isSecret;

  private String createdDate, modifiedDate;
  private String nickname;
  private Long postsId;

  public PostsCommentsResponseDto(PostsComments postsComments) {
    this.id = postsComments.getId();
    this.comment = postsComments.getComment();
    this.isSecret = postsComments.getIsSecret();
    this.createdDate = postsComments.getCreatedDate();
    this.modifiedDate = postsComments.getModifiedDate();
    this.nickname = postsComments.getUser().getNickname();
    this.postsId = postsComments.getPosts().getId();
  }
}
