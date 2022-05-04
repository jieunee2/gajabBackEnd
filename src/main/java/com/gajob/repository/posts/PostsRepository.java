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

  // 우선 좋아요 수를 기준으로 내림차순 정렬하고, 그 다음 조회수를 기준으로 내림차순으로 다중정렬
  @Query("SELECT p FROM Posts p ORDER BY p.likes DESC, p.view DESC")
  List<Posts> findAllDescByLikesAndView();

//  // 조회수를 기준으로 내림차순 정렬
//  @Query("SELECT p FROM Posts p ORDER BY p.view DESC")
//  List<Posts> findAllDescByView();


}
