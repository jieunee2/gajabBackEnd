package com.gajob.service.user;

import com.gajob.entity.user.User;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ProfileImageServiceImpl implements ProfileImageService {

  private final UserRepository userRepository;

  @Value("${profileImg.path}")
  private String uploadFolder;

  // 프로필 사진 업로드
  @Transactional
  public void upload(MultipartFile multipartFile) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    if (!multipartFile.isEmpty()) {
      String imageFileName = user.getId() + "_" + multipartFile.getOriginalFilename();
      Path imageFilePath = Paths.get(uploadFolder + imageFileName);
      try {
        // 프로필 사진이 존재할 경우, 기존의 파일은 삭제
        if (user.getProfileImg() != null) {
          File file = new File(uploadFolder + user.getProfileImg());
          file.delete();
        }
        Files.write(imageFilePath, multipartFile.getBytes());
      } catch (Exception e) {
        e.printStackTrace();
      }
      user.profileImgUpdate(imageFileName);
    }
  }

//  @Transactional
//  public void delete() {
//    User user = userRepository.findOneWithAuthoritiesByEmail(
//        SecurityUtil.getCurrentUsername().get()).get();
//
//    if (!(user.getProfileImg() == null)) {
//      userRepository.deleteProfileImg(user.getProfileImg());
//    }
}


