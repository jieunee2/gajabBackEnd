package com.gajob.service.user;

import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageService {

  void upload(MultipartFile multipartFile); // 프로필 사진 업로드

  void delete(); // 프로필 사진 삭제
}
