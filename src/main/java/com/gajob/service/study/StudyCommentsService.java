package com.gajob.service.study;

import com.gajob.dto.study.StudyCommentsDto;
import com.gajob.dto.study.StudyCommentsResponseDto;

public interface StudyCommentsService {

  StudyCommentsResponseDto save(Long id, StudyCommentsDto studyCommentsDto); //댓글 저장

  StudyCommentsResponseDto update(Long postId, Long commentId,
      StudyCommentsDto studyCommentsDto); //댓글 수정

  String delete(Long commentId); //댓글 삭제

}
