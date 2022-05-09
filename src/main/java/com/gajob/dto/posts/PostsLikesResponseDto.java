package com.gajob.dto.posts;

import com.gajob.entity.posts.PostsLikes;
import lombok.Data;

@Data
public class PostsLikesResponseDto {

  private Long id;
  private String nickname;

  public PostsLikesResponseDto(PostsLikes postsLikes) {
    this.id = postsLikes.getId();
    this.nickname = postsLikes.getUser().getNickname();
  }

}
