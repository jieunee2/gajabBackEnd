package com.gajob.service.study;

import com.gajob.dto.study.StudyDto;
import com.gajob.dto.study.StudyReadDto;
import com.gajob.dto.study.StudyResponseDto;
import com.gajob.entity.study.Study;
import java.util.List;

public interface StudyService {

  void statusChange(); //현재 날짜가 스터디 종료 날짜보다 지났을 경우 Status의 상태를 모집종료로 변경

  StudyResponseDto save(StudyDto studyDto); //게시물 저장

  StudyReadDto getPosts(Long postId); //게시물 낱개 조회 및 조회수 증가

  List<StudyReadDto> getAllPosts(); //게시물 전체 조회

  boolean isLikeStatus(Study study); //좋아요 여부 확인

  boolean isScrapStatus(Study study); //스크랩 여부 확인

  boolean isApplyStatus(Study study); // 스터디 지원 여부 확인

  StudyReadDto update(Long postId, StudyDto studyDto); //게시물 수정

  String delete(Long postId); //게시물 삭제

}
