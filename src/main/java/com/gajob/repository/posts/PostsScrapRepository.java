package com.gajob.repository.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.entity.posts.PostsScrap;
import com.gajob.entity.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsScrapRepository extends JpaRepository<PostsScrap, Long> {

  @Query("select p from PostsScrap p")
  List<PostsScrap> findAll();

  Optional<PostsScrap> findByUserAndPosts(User user, Posts posts);

  Optional<PostsScrap> findByUser(User user);

  Optional<PostsScrap> deleteByUserAndPosts(User user, Posts posts);

}
