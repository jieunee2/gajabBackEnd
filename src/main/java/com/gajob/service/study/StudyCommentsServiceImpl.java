package com.gajob.service.study;

import com.gajob.dto.study.StudyCommentsDto;
import com.gajob.dto.study.StudyCommentsResponseDto;
import com.gajob.entity.study.Study;
import com.gajob.entity.study.StudyComments;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.study.StudyCommentsRepository;
import com.gajob.repository.study.StudyRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StudyCommentsServiceImpl implements StudyCommentsService {

  private final UserRepository userRepository;
  private final StudyRepository studyRepository;
  private final StudyCommentsRepository studyCommentsRepository;

  // 사용자가 등록한 댓글을 DB에 저장
  @Transactional
  public StudyCommentsResponseDto save(Long postId, StudyCommentsDto studyCommentsDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();
    Study study = studyRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    return new StudyCommentsResponseDto(studyCommentsRepository.save(
        studyCommentsDto.toEntity(user, study)));
  }

  // 댓글 수정
  @Transactional
  public StudyCommentsResponseDto update(Long postId, Long commentId,
      StudyCommentsDto studyCommentsDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Study study = studyRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    StudyComments studyComments = studyCommentsRepository.findById(commentId)
        .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_ID_NOT_EXIST));

    // 현재 로그인한 유저와 댓글 작성자의 이메일이 일치하지 않을 경우, 에러 발생
    if (!(studyComments.getUser().getEmail().equals(user.getEmail()))) {
      throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
    }

    studyComments.update(studyCommentsDto.getComment());

    StudyCommentsResponseDto studyCommentsResponseDto = new StudyCommentsResponseDto(studyComments);

    return studyCommentsResponseDto;
  }

  // 댓글 삭제
  @Transactional
  public String delete(Long commentId) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    StudyComments studyComments = studyCommentsRepository.findById(commentId)
        .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_ID_NOT_EXIST));

    // 현재 로그인한 유저와 댓글 작성자의 이메일이 일치하지 않을 경우, 에러 발생
    if (!(studyComments.getUser().getEmail().equals(user.getEmail()))) {
      throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
    }

    studyCommentsRepository.delete(studyComments);

    return "comments-delete";
  }

}
