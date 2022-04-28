package com.gajob.repository.posts;

import com.gajob.entity.posts.PostsComments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsCommentsRepository extends JpaRepository<PostsComments, Long> {

  List<PostsComments> findAllByPostsId(Long postId);

}
