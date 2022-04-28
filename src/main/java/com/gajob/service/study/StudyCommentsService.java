package com.gajob.service.study;

import com.gajob.dto.study.StudyCommentsDto;
import com.gajob.dto.study.StudyCommentsResponseDto;
import java.util.List;

public interface StudyCommentsService {

  StudyCommentsResponseDto save(Long postId, StudyCommentsDto studyCommentsDto); //댓글 저장

  List<StudyCommentsResponseDto> getStudyComments(Long postId); //게시물 별로 댓글 조회

  StudyCommentsResponseDto update(Long postId, Long commentId,
      StudyCommentsDto studyCommentsDto); //댓글 수정

  String delete(Long commentId); //댓글 삭제

}
