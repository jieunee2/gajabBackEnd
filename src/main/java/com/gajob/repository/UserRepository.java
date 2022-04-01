package com.gajob.repository;

import com.gajob.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  // username을 기준으로 User 정보(authorities 정보 포함)를 가져오는 역할을 수행
  @EntityGraph(attributePaths = "authorities")
  Optional<User> findOneWithAuthoritiesByUsername(String username);

}
