package com.gajob.repository.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.entity.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Posts, Long> {

  @Modifying
  @Query("update Posts p set p.view=p.view+1 where p.id = :id")
  int updateView(Long id);

  @Query("select p from Posts p")
  List<Posts> findAll();

  List<Posts> findAllByUser(User user);
}
