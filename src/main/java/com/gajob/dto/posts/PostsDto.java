package com.gajob.dto.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.entity.user.User;
import com.gajob.enumtype.Category;
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
  private Category category;
  private User user;

  public Posts toEntity(User user) {
    Posts posts = Posts.builder().title(title).content(content).category(category).view(0)
        .writer(user.getNickname()).user(user).build();

    return posts;
  }
}
