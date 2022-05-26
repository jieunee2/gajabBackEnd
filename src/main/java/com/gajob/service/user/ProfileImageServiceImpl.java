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

  @Transactional
  public void upload(MultipartFile multipartFile) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    if (!multipartFile.isEmpty()) {
      String imageFileName = user.getId() + "_" + multipartFile.getOriginalFilename();
      Path imageFilePath = Paths.get(uploadFolder + imageFileName);
      try {
        // 프로필 사진이 존재할 경우, 기존의 파일은 삭제
        if (user.getProfileFilePath() != null) {
          File file = new File(user.getProfileFilePath());

          file.delete();
        }
        Files.write(imageFilePath, multipartFile.getBytes());
      } catch (Exception e) {
        e.printStackTrace();
      }
      user.setProfileFilePath(imageFilePath.toString());
    }
  }

  // 프로필 사진 삭제
  @Transactional
  public void delete() {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    // 프로필 사진이 존재할 경우, file을 먼저 삭제 후 user의 프로필 데이터를 null 로 변경
    if (!(user.getProfileFilePath() == null)) {
      File file = new File(user.getProfileFilePath());
      file.delete();

      user.setProfileFilePath(null);
    }
  }

//  // 프로필 사진 조회
//  @Transactional
//  public byte[] getProfile() throws IOException {
//    User user = userRepository.findOneWithAuthoritiesByEmail(
//        Sethrows IOExceptioncurityUtil.getCurrentUsername().get()).get();
//
//    // 등록된 프로필 사진이 없을 시 null 리턴
//    if (user.getProfileImage() == null) {
//      return null;
//    }
//    // 유저의 프로필 사진의 경로를 찾아서 조회
//    InputStream imageStram = new FileInputStream(
//        uploadFolder + user.getProfileImage().getFileName());
//
//    // byte 형식으로 변환하여 이미지 출력
//    byte[] imageByteArray = IOUtils.toByteArray(imageStram);
//    imageStram.close();
//
//    return imageByteArray;
//  }

//  @Transactional
//  public void upload(MultipartFile multipartFile) {
//    User user = userRepository.findOneWithAuthoritiesByEmail(
//        SecurityUtil.getCurrentUsername().get()).get();
//
//    if (!multipartFile.isEmpty()) {
//      String imageFileName = user.getId() + "_" + multipartFile.getOriginalFilename();
//      Path imageFilePath = Paths.get(uploadFolder + imageFileName);
//      try {
//        // 프로필 사진이 존재할 경우, 기존의 파일은 삭제
//        if (user.getProfileImage() != null) {
//          File file = new File(uploadFolder + user.getProfileImage().getFileName());
//          file.delete();
////          profileImageRepository.deleteById(user.getProfileImage().getId());
//        }
//        Files.write(imageFilePath, multipartFile.getBytes());
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//
//      ProfileImageDto profileImageDto = new ProfileImageDto(imageFileName,
//          imageFilePath.toString());
//      user.setProfileImage(profileImageDto.toEntity());
//
//      // 유저 정보 조회 시 프로필 사진 파일 경로를 조회할 수 있도록 저장
//      UserDto userDto = new UserDto(user);
//      userDto.setProfileFilePath(profileImageDto.getFilePath());
//
//    }
//  }
//
//  // 프로필 사진 조회
//  @Transactional
//  public byte[] getProfile() throws IOException {
//    User user = userRepository.findOneWithAuthoritiesByEmail(
//        SecurityUtil.getCurrentUsername().get()).get();
//
//    // 등록된 프로필 사진이 없을 시 null 리턴
//    if (user.getProfileImage() == null) {
//      return null;
//    }
//    // 유저의 프로필 사진의 경로를 찾아서 조회
//    InputStream imageStram = new FileInputStream(
//        uploadFolder + user.getProfileImage().getFileName());
//
//    // byte 형식으로 변환하여 이미지 출력
//    byte[] imageByteArray = IOUtils.toByteArray(imageStram);
//    imageStram.close();
//
//    return imageByteArray;
//  }
//
//  // 프로필 사진 삭제
//  @Transactional
//  public void delete() {
//    User user = userRepository.findOneWithAuthoritiesByEmail(
//        SecurityUtil.getCurrentUsername().get()).get();
//
//    // 프로필 사진이 존재할 경우, file을 먼저 삭제 후 user의 프로필 데이터를 null 로 변경
//    if (!(user.getProfileImage() == null)) {
//      File file = new File(uploadFolder + user.getProfileImage().getFileName());
//      file.delete();
//
//      user.setProfileImage(null);
//    }
//  }
}