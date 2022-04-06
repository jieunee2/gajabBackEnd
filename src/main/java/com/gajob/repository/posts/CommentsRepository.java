package com.gajob.repository.posts;

import com.gajob.entity.posts.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
