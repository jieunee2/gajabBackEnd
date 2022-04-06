package com.gajob.service.posts;

import com.gajob.dto.posts.CommentsDto;
import com.gajob.dto.posts.CommentsResponseDto;

public interface CommentsService {

  CommentsResponseDto save(Long id, CommentsDto commentsDto); //댓글 저장

}