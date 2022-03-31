package com.gajob.repository;

import com.gajob.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  User findByNickname(String nickname);

  // 중복인 값이 들어올 경우 true, 아니면 false 리턴
  boolean existsByUsername(String username);

  boolean existsByNickname(String nickname);

}
