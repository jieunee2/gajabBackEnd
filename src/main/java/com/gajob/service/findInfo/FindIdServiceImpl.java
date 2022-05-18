package com.gajob.service.findInfo;

import com.gajob.dto.user.UserDto;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindIdServiceImpl implements FindIdService {

  private final UserRepository userRepository;

  @Transactional
  public String findId(UserDto userDto) {
    // 사용자의 학번을 입력받고 이로 조회 시 결과가 없으면 에러문 출력
    User user = userRepository.findByStudentId(userDto.getStudentId())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    // 사용자의 아이디(이메일) 출력
    return user.getEmail();
  }

}
