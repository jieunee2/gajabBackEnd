package com.gajob.dto.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.entity.user.User;
import com.gajob.enumtype.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsDto {

  private String title;
  private String content;
  private PostCategory postCategory;
  private User user;

  public Posts toEntity(User user) {
    Posts posts = Posts.builder().title(title).content(content).postCategory(postCategory).view(0)
        .writer(user.getNickname()).user(user).build();

    return posts;
  }
}
