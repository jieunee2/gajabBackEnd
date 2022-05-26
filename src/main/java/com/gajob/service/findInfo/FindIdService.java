package com.gajob.service.findInfo;

import com.gajob.dto.user.UserDto;
import com.gajob.repository.user.UserMapping;
import java.util.List;

public interface FindIdService {

  List<UserMapping> findId(UserDto userDto); // 아이디 찾기

}
