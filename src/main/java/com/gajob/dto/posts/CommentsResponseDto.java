package com.gajob.dto.posts;

import com.gajob.entity.posts.Comments;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class CommentsResponseDto {

  private Long id;
  private String comment;
  private String createdDate = LocalDateTime.now().format(
      DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
  private String modifiedDate = LocalDateTime.now()
      .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
  private String nickname;
  private Long postsId;

  public CommentsResponseDto(Comments comments) {
    this.id = comments.getId();
    this.comment = comments.getComment();
    this.createdDate = comments.getCreatedDate();
    this.modifiedDate = comments.getModifiedDate();
    this.nickname = comments.getUser().getNickname();
    this.postsId = comments.getPosts().getId();
  }
}
