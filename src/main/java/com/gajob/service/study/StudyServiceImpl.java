package com.gajob.service.study;

import com.gajob.dto.study.StudyDto;
import com.gajob.dto.study.StudyReadDto;
import com.gajob.dto.study.StudyResponseDto;
import com.gajob.entity.study.Study;
import com.gajob.entity.user.User;

import com.gajob.enumtype.ErrorCode;
import com.gajob.enumtype.Status;
import com.gajob.exception.CustomException;
import com.gajob.repository.study.StudyRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import java.time.LocalDate;
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

//    statusChange(postId);

    StudyReadDto studyReadDto = new StudyReadDto(study);

    return studyReadDto;
  }

  // 게시물 전체 조회 (이때는 조회수 증가 안함)
  @Transactional(readOnly = true)
  public List<StudyReadDto> getAllPosts() {
    // studyRepository로 결과로 넘어온 Study의 Stream을 map을 통해 StudyReadDto로 변환
    return studyRepository.findAll().stream().map(StudyReadDto::new).collect(Collectors.toList());
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
