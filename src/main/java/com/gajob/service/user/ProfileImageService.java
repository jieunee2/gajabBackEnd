package com.gajob.service.user;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageService {

  void upload(MultipartFile multipartFile); // 프로필 사진 업로드

  byte[] getProfile() throws IOException; // 프로필 사진 조회

  void delete(); // 프로필 사진 삭제
}
