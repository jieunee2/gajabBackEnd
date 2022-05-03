package com.gajob.service.posts;

import com.gajob.dto.posts.PostsReadDto;
import java.util.List;

public interface HotPostsService {

  List<PostsReadDto> getHotPosts();

}
