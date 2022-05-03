package com.gajob.dto.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.entity.user.User;
import com.gajob.enumtype.JobCategory;
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
  private JobCategory jobCategory;
  private User user;
//  private List<PostsLikes> likesList;

  public Posts toEntity(User user) {
    Posts posts = Posts.builder().title(title).content(content).postCategory(postCategory)
        .jobCategory(jobCategory).view(0).likes(0)
        .writer(user.getNickname()).user(user).build();

    return posts;
  }
}
