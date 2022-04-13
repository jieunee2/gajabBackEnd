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
  public StudyCommentsResponseDto save(Long id, StudyCommentsDto studyCommentsDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();
    Study study = studyRepository.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    return new StudyCommentsResponseDto(studyCommentsRepository.save(
        studyCommentsDto.toEntity(user, study)));
  }

  // 댓글 수정
  @Transactional
  public StudyCommentsResponseDto update(Long postId, Long commentId,
      StudyCommentsDto studyCommentsDto) {
    Study study = studyRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    StudyComments studyComments = studyCommentsRepository.findById(commentId)
        .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_ID_NOT_EXIST));

    studyComments.update(studyCommentsDto.getComment());

    StudyCommentsResponseDto studyCommentsResponseDto = new StudyCommentsResponseDto(studyComments);

    return studyCommentsResponseDto;
  }

  // 댓글 삭제
  @Transactional
  public String delete(Long commentId) {
    StudyComments studyComments = studyCommentsRepository.findById(commentId)
        .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_ID_NOT_EXIST));
    studyCommentsRepository.delete(studyComments);

    return "comments-delete";
  }

}
