package com.gajob.repository.posts;

import com.gajob.entity.posts.PostsComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsCommentsRepository extends JpaRepository<PostsComments, Long> {

}
