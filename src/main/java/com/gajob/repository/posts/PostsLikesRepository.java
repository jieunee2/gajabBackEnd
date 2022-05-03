package com.gajob.repository.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.entity.posts.PostsLikes;
import com.gajob.entity.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsLikesRepository extends JpaRepository<PostsLikes, Long> {

  Optional<PostsLikes> deleteByUserAndPosts(User user, Posts posts);

  Optional<PostsLikes> findByUserAndPosts(User user, Posts posts);
}
