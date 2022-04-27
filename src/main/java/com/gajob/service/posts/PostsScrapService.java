package com.gajob.service.posts;

import com.gajob.dto.posts.PostsScrapResponseDto;
import java.util.List;

public interface PostsScrapService {

  String scrap(Long postId); //스크랩 기능

  List<PostsScrapResponseDto> getScrap(); //스크랩 목록 조회

}
