package com.gajob.service.study;

import com.gajob.dto.study.StudyDto;
import com.gajob.dto.study.StudyLikesResponseDto;
import com.gajob.dto.study.StudyReadDto;
import com.gajob.dto.study.StudyRecruitmentResponseDto;
import com.gajob.dto.study.StudyResponseDto;
import com.gajob.dto.study.StudyScrapResponseDto;
import com.gajob.entity.study.Study;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.enumtype.Status;
import com.gajob.exception.CustomException;
import com.gajob.repository.study.StudyRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StudyServiceImpl implements StudyService {

  private final StudyRepository studyRepository;
  private final UserRepository userRepository;

  @Transactional
  public void statusChange() {
    List<Study> study = studyRepository.findAll();

    LocalDate nowDate = LocalDate.now();//현재 날짜 가져오기

    // 현재 날짜가 스터디 종료 날짜보다 지났을 경우 Status의 상태를 모집종료로 변경
    for (Study studyList : study) {
      if (nowDate.isAfter(studyList.getEndDate())) {
        studyList.setStatus(Status.모집종료);
        studyRepository.save(studyList);
      }
      // 현재 날짜가 스터디 모집 예정일 날짜보다 이전일 경우 Status의 상태를 모집예정일로 변경
      else if (nowDate.isBefore(studyList.getStartDate())) {
        studyList.setStatus(Status.모집예정일);
        studyRepository.save(studyList);
      }
    }
  }

  // 게시물을 DB에 저장
  @Transactional
  public StudyResponseDto save(StudyDto studyDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    return new StudyResponseDto(studyRepository.save(studyDto.toEntity(user)));
  }

  // 게시물 낱개 조회 및 조회수 증가
  @Transactional
  public StudyReadDto getPosts(Long postId) {
    studyRepository.updateView(postId);

    Study study = studyRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    StudyReadDto studyReadDto = new StudyReadDto(study);

    // 게시물 조회시 Study 테이블의 likes 컬럼 업데이트 및 유저의 게시물 좋아요 상태, 스터디 지원 상태 변경
    study.likeUpdate(studyReadDto.getLikes());
    studyReadDto.setLikeStatus(isLikeStatus(study));
    studyReadDto.setScrapStatus(isScrapStatus(study));
    studyReadDto.setApplyStatus(isApplyStatus(study));

    return studyReadDto;
  }

  // 게시물 전체 조회 (이때는 조회수 증가 안함)
  @Transactional(readOnly = true)
  public List<StudyReadDto> getAllPosts() {
    List<Study> study = studyRepository.findAll();

    ArrayList<StudyReadDto> studyReadDtos = new ArrayList<StudyReadDto>();

    // 게시물 조회시 Study 테이블의 likes 컬럼 업데이트 및 유저의 게시물 좋아요, 스크랩 상태, 스터디 지원 상태 변경
    for (Study studyList : study) {
      StudyReadDto studyReadDto = new StudyReadDto(studyList);
      studyList.likeUpdate(studyReadDto.getLikes());
      studyReadDto.setLikeStatus(isLikeStatus(studyList));
      studyReadDto.setScrapStatus(isScrapStatus(studyList));
      studyReadDto.setApplyStatus(isApplyStatus(studyList));

      // 변경된 데이터들을 studyReadDtos에 저장
      studyReadDtos.add(studyReadDto);
    }

    return studyReadDtos;
  }

  // 좋아요 여부 확인
  @Transactional
  public boolean isLikeStatus(Study study) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    List<StudyLikesResponseDto> likesList = study.getLikeList().stream()
        .map(StudyLikesResponseDto::new).collect(Collectors.toList());

    for (StudyLikesResponseDto studyLikesResponseDto : likesList) {
      // StudyLikesResponseDto 내에 현재 로그인 한 유저의 닉네임이 포함되어 있으면 likeStatus의 값을 true로 변환
      if (studyLikesResponseDto.getNickname()
          .equals(user.getNickname())) {
        StudyReadDto studyReadDto = new StudyReadDto(study);
        studyReadDto.setLikeStatus(true);
        return true;
      }
    }
    return false;
  }

  // 스크랩 여부 확인
  @Transactional
  public boolean isScrapStatus(Study study) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    List<StudyScrapResponseDto> scrapList = study.getStudyScrapList().stream()
        .map(StudyScrapResponseDto::new).collect(Collectors.toList());

    for (StudyScrapResponseDto studyScrapResponseDto : scrapList) {
      // StudyScrapResponseDto 내에 현재 로그인 한 유저의 닉네임이 포함되어 있으면 scrapStatus의 값을 true로 변환
      if (studyScrapResponseDto.getNickname()
          .equals(user.getNickname())) {
        StudyReadDto studyReadDto = new StudyReadDto(study);
        studyReadDto.setScrapStatus(true);
        return true;
      }
    }
    return false;
  }

  // 스터디 지원 여부 확인
  @Transactional
  public boolean isApplyStatus(Study study) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    List<StudyRecruitmentResponseDto> recruitmentList = study.getStudyRecruitmentList().stream()
        .map(StudyRecruitmentResponseDto::new).collect(Collectors.toList());

    System.out.println("리스트" + recruitmentList);

    for (StudyRecruitmentResponseDto studyRecruitmentResponseDto : recruitmentList) {
      // StudyRecruitmentResponseDto 내에 현재 로그인 한 유저의 이름이 포함되어 있으면 applyStatus 값을 true로 변환
      if (studyRecruitmentResponseDto.getName()
          .equals(user.getName())) {
        StudyReadDto studyReadDto = new StudyReadDto(study);
        studyReadDto.setApplyStatus(true);
        return true;
      }
    }
    return false;
  }

  // 게시물 수정
  @Transactional
  public StudyReadDto update(Long postId, StudyDto studyDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Study study = studyRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    // 현재 로그인한 유저와 게시물 작성자의 이메일이 일치하지 않을 경우, 에러 발생
    if (!(study.getUser().getEmail().equals(user.getEmail()))) {
      throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
    }

    study.update(studyDto.getTitle(), studyDto.getContent(), studyDto.getStudyCategory(),
        studyDto.getArea(), studyDto.getMinPeople(), studyDto.getMaxPeople(),
        studyDto.getStartDate(),
        studyDto.getEndDate(), studyDto.getOpenTalkUrl());

    StudyReadDto studyReadDto = new StudyReadDto(study);

    return studyReadDto;
  }

  // 게시물 삭제
  @Transactional
  public String delete(Long postId) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Study study = studyRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    // 현재 로그인한 유저와 게시물 작성자의 이메일이 일치하지 않을 경우, 에러 발생
    if (!(study.getUser().getEmail().equals(user.getEmail()))) {
      throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
    }

    studyRepository.delete(study);

    return "posts-delete";
  }

}
