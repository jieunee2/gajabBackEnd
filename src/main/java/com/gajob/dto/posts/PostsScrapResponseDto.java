package com.gajob.dto.posts;

import com.gajob.entity.posts.PostsScrap;
import com.gajob.enumtype.JobCategory;
import com.gajob.enumtype.PostCategory;
import lombok.Data;

@Data
public class PostsScrapResponseDto {

  private Long id;
  private String title;
  private PostCategory postCategory;
  private JobCategory jobCategory;
  private String writer;
  private String scrapDate;

  public PostsScrapResponseDto(PostsScrap postsScrap) {
    this.id = postsScrap.getId();
    this.title = postsScrap.getPosts().getTitle();
    this.postCategory = postsScrap.getPosts().getPostCategory();
    this.jobCategory = postsScrap.getPosts().getJobCategory();
    this.writer = postsScrap.getPosts().getWriter();
    this.scrapDate = postsScrap.getCreatedDate();
  }

}
